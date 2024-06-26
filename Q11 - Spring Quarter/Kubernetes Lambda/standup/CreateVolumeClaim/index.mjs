// This code was written with the help of an AI assistant.
import * as k8s from '@kubernetes/client-node';
import { EKSClient, DescribeClusterCommand } from "@aws-sdk/client-eks";

const eksClient = new EKSClient({ region: 'us-west-2' });

const getKubeconfig = async (clusterName) => {
    try {
        const command = new DescribeClusterCommand({ name: clusterName });
        const response = await eksClient.send(command);
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

        return kubeconfig;
    } catch (error) {
        console.error(`Error getting kubeconfig: ${error}`);
        throw error;
    }
};

export const handler = async (event) => {
    const clusterName = "Gezora-Godzooky-Cluster";

    try {
        // Get kubeconfig
        const kubeconfig = await getKubeconfig(clusterName);
        console.log("Kubeconfig Created\n", kubeconfig);

        // Load Kubernetes config
        const kc = new k8s.KubeConfig();
        kc.loadFromString(kubeconfig);
        console.log('Loaded kubeconfig');
        
        // Get the bearer token using aws-iam-authenticator
        const k8sApi = kc.makeApiClient(k8s.CoreV1Api);
        console.log('Created K8s API client');

        // Define the Persistent Volume
        const pv = {
            apiVersion: 'v1',
            kind: 'PersistentVolume',
            metadata: { name: 'efs-pv' },
            spec: {
                capacity: { storage: '5Gi' },
                volumeMode: 'Filesystem',
                accessModes: ['ReadWriteMany'],
                persistentVolumeReclaimPolicy: 'Retain',
                storageClassName: '',
                csi: {
                    driver: 'efs.csi.aws.com',
                    volumeHandle: 'fs-0eb1f98623d53e017'
                }
            }
        };

        // Define the Persistent Volume Claim
        const pvc = {
            apiVersion: 'v1',
            kind: 'PersistentVolumeClaim',
            metadata: { name: 'efs-pvc', namespace: 'default' },
            spec: {
                accessModes: ['ReadWriteMany'],
                storageClassName: '',
                resources: { requests: { storage: '5Gi' } },
                volumeName: 'efs-pv'
            }
        };

        // Create the Persistent Volume
        await k8sApi.createPersistentVolume(pv);
        console.log('Created PV');

        // Create the Persistent Volume Claim
        await k8sApi.createNamespacedPersistentVolumeClaim('default', pvc);
        console.log('Created PVC');

        return {
            statusCode: 200,
            body: JSON.stringify({ message: 'PV and PVC created successfully' })
        };
    } catch (error) {
        console.error('Error creating PV or PVC:', error);
        return {
            statusCode: 500,
            body: JSON.stringify({ error: 'Error creating PV or PVC' })
        };
    }
};
// This code was written with the help of an AI assistant.
