// This code was written with the help of an AI assistant.
import { EKSClient, DeleteClusterCommand } from "@aws-sdk/client-eks";

const client = new EKSClient({ region: "us-west-2" });

export const handler = async (event) => {
    const clusterName = "Gezora-Godzooky-Cluster";

    try {
        const command = new DeleteClusterCommand({ name: clusterName });
        await client.send(command);
        return {
            statusCode: 200,
            body: JSON.stringify(`EKS cluster ${clusterName} deletion initiated`)
        };
    } catch (error) {
        console.error(`Error deleting EKS cluster: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error deleting EKS cluster')
        };
    }
};
