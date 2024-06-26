// This code was written with the help of an AI assistant.
const { KubeConfig, BatchV1Api } = require('@kubernetes/client-node');

const checkJobStatuses = async (kubeconfigContent, namespace) => {
    const kc = new KubeConfig();
    kc.loadFromString(kubeconfigContent);
    const batchV1Api = kc.makeApiClient(BatchV1Api);

    try {
        const { body: { items: jobs } } = await batchV1Api.listNamespacedJob(namespace);
        const incompleteJobs = jobs.filter(job => !job.status.succeeded || job.status.succeeded < 1);
        return incompleteJobs.length === 0;
    } catch (error) {
        console.error(`Error listing jobs: ${error}`);
        throw error;
    }
};

exports.handler = async (event) => {
    const kubeconfig = event.kubeconfig;
    const namespace = "fargate-pods";  // Adjust if needed

    try {
        const jobsComplete = await checkJobStatuses(kubeconfig, namespace);
        return {
            statusCode: 200,
            body: JSON.stringify({ jobsComplete })
        };
    } catch (error) {
        console.error(`Error in CheckJobStatus handler: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify({ error: error.message })
        };
    }
};
