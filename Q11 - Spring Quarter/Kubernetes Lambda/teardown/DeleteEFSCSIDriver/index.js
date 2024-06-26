// This code was written with the help of an AI assistant.
const AWS = require('aws-sdk');
const k8s = require('@kubernetes/client-node');
const fs = require('fs');
const axios = require('axios');

const eks = new AWS.EKS();

const deleteK8sResource = async (kubeconfigPath, resourceType, resourceName, namespace = 'default') => {
    const kc = new k8s.KubeConfig();
    kc.loadFromFile(kubeconfigPath);
    const k8sApi = kc.makeApiClient(k8s.KubernetesObjectApi);

    try {
        await k8sApi.delete({
            apiVersion: 'v1',
            kind: resourceType,
            metadata: {
                name: resourceName,
                namespace: namespace
            }
        });
        console.info(`${resourceType} ${resourceName} deleted successfully`);
    } catch (error) {
        console.error(`Error deleting ${resourceType} ${resourceName}: ${error}`);
        throw error;
    }
};

exports.handler = async (event) => {
    const { kubeconfigContent } = event;

    try {
        // Save kubeconfig content to a temporary file
        const kubeconfigPath = '/tmp/kubeconfig';
        fs.writeFileSync(kubeconfigPath, kubeconfigContent);

        // Delete EFS CSI driver resources
        await deleteK8sResource(kubeconfigPath, 'Deployment', 'efs-csi-controller', 'kube-system');
        await deleteK8sResource(kubeconfigPath, 'DaemonSet', 'efs-csi-node', 'kube-system');
        await deleteK8sResource(kubeconfigPath, 'ServiceAccount', 'efs-csi-controller-sa', 'kube-system');
        await deleteK8sResource(kubeconfigPath, 'ClusterRole', 'efs-csi-controller-role');
        await deleteK8sResource(kubeconfigPath, 'ClusterRoleBinding', 'efs-csi-controller-binding');

        // Delete EFS PV and PVC
        await deleteK8sResource(kubeconfigPath, 'PersistentVolume', 'efs-pv');
        await deleteK8sResource(kubeconfigPath, 'PersistentVolumeClaim', 'efs-pvc', 'fargate-pods');

        return {
            statusCode: 200,
            body: JSON.stringify('EFS CSI driver and associated resources deleted successfully')
        };
    } catch (error) {
        console.error(`Error in handler: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error deleting EFS CSI driver and associated resources')
        };
    }
};
