import { EKSClient, DescribeClusterCommand, AssociateIdentityProviderConfigCommand } from "@aws-sdk/client-eks";
import { IAMClient, UpdateAssumeRolePolicyCommand } from "@aws-sdk/client-iam";
import { KubeConfig, CoreV1Api } from '@kubernetes/client-node';
import { execSync } from 'child_process';

const region = "us-west-2"; // Replace with your AWS region
const clusterName = "Gezora-Godzooky-Cluster"; // Replace with your EKS cluster name
const accountId = "993145419620"; // Replace with your AWS account ID
const namespace = "default"; // Replace with your Kubernetes namespace
const serviceAccountName = "GezoraGodzookyServiceRole"; // Replace with your Kubernetes service account name
const roleName = "GezoraGodzookyClusterRole"; // Replace with your IAM role name

const eksClient = new EKSClient({ region });
const iamClient = new IAMClient({ region });

export const handler = async (event) => {
  try {
    // Step 1: Describe the EKS Cluster to get the OIDC provider URL
    const describeClusterCommand = new DescribeClusterCommand({ name: clusterName });
    const clusterData = await eksClient.send(describeClusterCommand);
    const oidcProviderUrl = clusterData.cluster.identity.oidc.issuer;
    const oidcProviderId = oidcProviderUrl.split('/').pop();
    const oidcArn = `arn:aws:iam::${accountId}:oidc-provider/${oidcProviderUrl}`;

    const cluster = clusterData.cluster;
    const clusterCert = Buffer.from(cluster.certificateAuthority.data, 'base64').toString('utf8');
    const clusterUrl = cluster.endpoint;

    console.log(`OIDC provider ${oidcProviderId} associated with cluster ${clusterName}.`);

    // Step 3: Update IAM Role Trust Relationship
    const trustPolicy = {
      Version: "2012-10-17",
      Statement: [
        {
          Effect: "Allow",
          Principal: {
            Federated: oidcArn,
          },
          Action: "sts:AssumeRoleWithWebIdentity",
          Condition: {
            StringEquals: {
              [`${oidcProviderUrl}:sub`]: `system:serviceaccount:${namespace}:${serviceAccountName}`,
            },
          },
        },
      ],
    };

    await iamClient.send(new UpdateAssumeRolePolicyCommand({
      RoleName: roleName,
      PolicyDocument: JSON.stringify(trustPolicy),
    }));

    console.log(`IAM role ${roleName} trust policy updated.`);

    // Test aws-iam-authenticator execution
    let tokenOutput;
    try {
      tokenOutput = execSync('./aws-iam-authenticator token -i ' + clusterName).toString();
      console.log('aws-iam-authenticator output:', tokenOutput);
    } catch (error) {
      console.error('Error executing aws-iam-authenticator:', error);
      throw new Error('aws-iam-authenticator execution failed.');
    }

    const kubeconfig = {
      apiVersion: "v1",
      clusters: [
        {
          cluster: {
            "certificate-authority-data": clusterCert,
            server: clusterUrl,
          },
          name: "kubernetes",
        },
      ],
      contexts: [
        {
          context: {
            cluster: "kubernetes",
            user: "aws",
          },
          name: "aws",
        },
      ],
      "current-context": "aws",
      kind: "Config",
      users: [
        {
          name: "aws",
          user: {
            exec: {
              apiVersion: "client.authentication.k8s.io/v1alpha1",
              command: "./aws-iam-authenticator",
              args: [
                "token",
                "-i",
                clusterName,
              ],
            },
          },
        },
      ],
    };

    // Step 4: Create Kubernetes Service Account
    const kc = new KubeConfig();
    kc.loadFromString(JSON.stringify(kubeconfig));
    const k8sApi = kc.makeApiClient(CoreV1Api);

    const serviceAccountManifest = {
      apiVersion: "v1",
      kind: "ServiceAccount",
      metadata: {
        name: serviceAccountName,
        namespace: namespace,
        annotations: {
          "eks.amazonaws.com/role-arn": `arn:aws:iam::${accountId}:role/${roleName}`,
        },
      },
    };

    await k8sApi.createNamespacedServiceAccount(namespace, serviceAccountManifest);
    console.log(`Kubernetes Service Account ${serviceAccountName} created in namespace ${namespace}.`);

    return {
      statusCode: 200,
      body: JSON.stringify('Resources configured successfully.'),
    };
  } catch (error) {
    console.error(error);
    return {
      statusCode: 500,
      body: JSON.stringify('Error configuring resources: ' + error.message),
    };
  }
};
// This code was written by an AI assistant.
