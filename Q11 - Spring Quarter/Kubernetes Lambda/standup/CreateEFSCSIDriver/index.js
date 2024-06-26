// This code was written with the help of an AI assistant.
const k8s = require('@kubernetes/client-node');
const axios = require('axios');

const fetchYamlFromUrl = async (url) => {
    try {
        const response = await axios.get(url);
        return response.data;
    } catch (error) {
        console.error(`Error fetching YAML from URL: ${error}`);
        throw error;
    }
};

const installEFSCsiDriver = async (kubeconfigContent) => {
    const kc = new k8s.KubeConfig();
    kc.loadFromString(kubeconfigContent);
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

exports.handler = async (event) => {
    const { kubeconfig } = event;

    try {
        await installEFSCsiDriver(kubeconfig);
        return {
            statusCode: 200,
            body: JSON.stringify('EFS CSI driver installed successfully')
        };
    } catch (error) {
        console.error(`Error installing EFS CSI driver: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error installing EFS CSI driver')
        };
    }
};
