// This code was written with the help of an AI assistant.
const AWS = require('aws-sdk');
const axios = require('axios');
const k8s = require('@kubernetes/client-node');
const fs = require('fs');


// Set the region for AWS SDK
AWS.config.update({ region: 'us-west-2' });

const iam = new AWS.IAM();
const ec2 = new AWS.EC2();
const eks = new AWS.EKS();

const createIAMRole = async (roleName) => {
    const assumeRolePolicy = {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Effect": "Allow",
                "Principal": {
                    "Service": "eks.amazonaws.com"
                },
                "Action": "sts:AssumeRole"
            }
        ]
    };

    try {
        const createRoleResponse = await iam.createRole({
            RoleName: roleName,
            AssumeRolePolicyDocument: JSON.stringify(assumeRolePolicy),
            Description: 'Role for EKS cluster'
        }).promise();

        const policyArns = [
            'arn:aws:iam::aws:policy/AmazonEKSClusterPolicy',
            'arn:aws:iam::aws:policy/AmazonEKSServicePolicy'
        ];

        for (const policyArn of policyArns) {
            await iam.attachRolePolicy({
                RoleName: roleName,
                PolicyArn: policyArn
            }).promise();
        }

        return createRoleResponse.Role.Arn;
    } catch (error) {
        console.error(`Error creating IAM role: ${error}`);
        throw error;
    }
};

const createVPC = async () => {
    try {
        const vpcResponse = await ec2.createVpc({
            CidrBlock: '10.0.0.0/16'
        }).promise();

        const vpcId = vpcResponse.Vpc.VpcId;

        const subnetResponse1 = await ec2.createSubnet({
            VpcId: vpcId,
            CidrBlock: '10.0.1.0/24'
        }).promise();

        const subnetResponse2 = await ec2.createSubnet({
            VpcId: vpcId,
            CidrBlock: '10.0.2.0/24'
        }).promise();

        const securityGroupResponse = await ec2.createSecurityGroup({
            GroupName: 'eks-security-group',
            GroupDescription: 'Security group for EKS cluster',
            VpcId: vpcId
        }).promise();

        return { vpcId, subnets: [subnetResponse1.Subnet.SubnetId, subnetResponse2.Subnet.SubnetId], securityGroupId: securityGroupResponse.GroupId };
    } catch (error) {
        console.error(`Error creating VPC: ${error}`);
        throw error;
    }
};

const getLatestEKSVersion = async () => {
    try {
        const response = await eks.describeAddonVersions().promise();
        const versions = response.addons[0].addonVersions.sort((a, b) => b.addonVersion.localeCompare(a.addonVersion));
        const latestVersion = versions[0].addonVersion;
        const secondLatestVersion = versions[1].addonVersion;
        return { latestVersion, secondLatestVersion };
    } catch (error) {
        console.error(`Error fetching EKS versions: ${error}`);
        throw error;
    }
};

const createEKSCluster = async (clusterName, roleArn, subnets, securityGroup, version) => {
    try {
        const response = await eks.createCluster({
            name: clusterName,
            version: version,
            roleArn: roleArn,
            resourcesVpcConfig: {
                subnetIds: subnets,
                securityGroupIds: [securityGroup]
            }
        }).promise();

        return response;
    } catch (error) {
        console.error(`Error creating EKS cluster: ${error}`);
        throw error;
    }
};

const waitForClusterActive = async (clusterName, timeout = 1800, interval = 30) => {
    const startTime = Date.now();
    let backoff = interval;

    while ((Date.now() - startTime) / 1000 < timeout) {
        try {
            const response = await eks.describeCluster({ name: clusterName }).promise();
            const status = response.cluster.status;
            if (status === 'ACTIVE') {
                return response;
            } else if (status === 'FAILED') {
                throw new Error(`Cluster ${clusterName} creation failed.`);
            }
            console.info(`Waiting for cluster ${clusterName} to become active. Current status: ${status}`);
            await new Promise(resolve => setTimeout(resolve, backoff * 1000));
            backoff = Math.min(backoff * 2, 300); // Exponential backoff with a max of 5 minutes
        } catch (error) {
            console.error(`Error describing cluster: ${error}`);
            throw error;
        }
    }

    throw new Error(`Timed out waiting for cluster ${clusterName} to become active.`);
};

const getKubeconfig = async (clusterName) => {
    try {
        const response = await eks.describeCluster({ name: clusterName }).promise();
        const endpoint = response.cluster.endpoint;
        const caData = response.cluster.certificateAuthority.data;

        const kubeconfig = `
        apiVersion: v1
        clusters:
        - cluster:
            certificate-authority-data: ${caData}
            server: ${endpoint}
          name: kubernetes
        contexts:
        - context:
            cluster: kubernetes
            user: aws
          name: aws
        current-context: aws
        kind: Config
        preferences: {}
        users:
        - name: aws
          user:
            exec:
              apiVersion: client.authentication.k8s.io/v1alpha1
              command: aws-iam-authenticator
              args:
                - "token"
                - "-i"
                - "${clusterName}"
        `;
        fs.writeFileSync('/tmp/kubeconfig', kubeconfig);
        return '/tmp/kubeconfig';
    } catch (error) {
        console.error(`Error getting kubeconfig: ${error}`);
        throw error;
    }
};

// Function to fetch YAML from a URL
const fetchYamlFromUrl = async (url) => {
    try {
        const response = await axios.get(url);
        return response.data;
    } catch (error) {
        console.error(`Error fetching YAML from URL: ${error}`);
        throw error;
    }
};

// Function to install EFS CSI driver
const installEFSCsiDriver = async (kubeconfigPath) => {
    const kc = new k8s.KubeConfig();
    kc.loadFromFile(kubeconfigPath);
    const k8sApi = kc.makeApiClient(k8s.KubernetesObjectApi);

    const url = 'https://raw.githubusercontent.com/kubernetes-sigs/aws-efs-csi-driver/master/deploy/kubernetes/base/deployment.yaml';
    const efsCsiDriverYaml = await fetchYamlFromUrl(url);

    try {
        const resources = k8s.loadAllYaml(efsCsiDriverYaml);
        for (const resource of resources) {
            await k8sApi.create(resource);
        }
        console.info('EFS CSI driver installed successfully');
    } catch (error) {
        console.error(`Error installing EFS CSI driver: ${error}`);
        throw error;
    }
};

const createEFSPvPvc = async (kubeconfigPath, efsId) => {
    const kc = new k8s.KubeConfig();
    kc.loadFromFile(kubeconfigPath);
    const k8sApi = kc.makeApiClient(k8s.KubernetesObjectApi);

    const pvYaml = `
    apiVersion: v1
    kind: PersistentVolume
    metadata:
      name: efs-pv
    spec:
      capacity:
        storage: 5Gi
      volumeMode: Filesystem
      accessModes:
        - ReadWriteMany
      persistentVolumeReclaimPolicy: Retain
      storageClassName: ""
      csi:
        driver: efs.csi.aws.com
        volumeHandle: ${efsId}
    `;

    const pvcYaml = `
    apiVersion: v1
    kind: PersistentVolumeClaim
    metadata:
      name: efs-pvc
      namespace: fargate-pods
    spec:
      accessModes:
        - ReadWriteMany
      storageClassName: ""
      resources:
        requests:
          storage: 5Gi
      volumeName: efs-pv
    `;

    const env = {
        KUBECONFIG: '/tmp/kubeconfig',
        PATH: process.env.PATH
    };

    try {
        await k8sApi.create(k8s.loadYaml(pvYaml));
        await k8sApi.create(k8s.loadYaml(pvcYaml));
        console.info('EFS StorageClass, PV, and PVC created successfully');
    } catch (error) {
        console.error(`Error creating EFS storage class, PV, and PVC: ${error}`);
        throw error;
    }
};

exports.handler = async (event) => {
    const clusterName = "godzooky-testing-cluster";
    const roleName = "godzooky-eks-role";
    const efsId = "fs-0eb1f98623d53e017";

    try {
        // Create IAM role
        const roleArn = await createIAMRole(roleName);

        // Create VPC, subnets, and security group
        const { vpcId, subnets, securityGroupId } = await createVPC();

        // Get the latest and second latest EKS versions
        const { latestVersion, secondLatestVersion } = await getLatestEKSVersion();

        // Create the EKS cluster with the latest version
        await createEKSCluster(clusterName, roleArn, subnets, securityGroupId, latestVersion);

        // Wait for the cluster to be active
        await waitForClusterActive(clusterName);

        // Get kubeconfig for the cluster
        const kubeconfigPath = await getKubeconfig(clusterName);

        // Install EFS CSI driver
        await installEFSCsiDriver(kubeconfigPath);

        // Create EFS storage class, PV, and PVC
        await createEFSPvPvc(kubeconfigPath, efsId);

        return {
            statusCode: 200,
            body: JSON.stringify('EKS cluster creation with EFS attachment initiated')
        };
    } catch (error) {
        console.error(`Error in handler: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error initiating EKS cluster creation')
        };
    }
};