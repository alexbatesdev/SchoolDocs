// This code was written with the help of an AI assistant.
import { EKSClient, DescribeClusterCommand } from "@aws-sdk/client-eks";

const client = new EKSClient({ region: "us-west-2" });

export const handler = async (event) => {
    const clusterName = "Gezora-Godzooky-Cluster";

    try {
        const command = new DescribeClusterCommand({ name: clusterName });
        const response = await client.send(command);
        const clusterStatus = response.cluster.status;
        return {
            statusCode: 200,
            body: JSON.stringify({ clusterStatus })
        };
    } catch (error) {
        console.error(`Error checking EKS cluster status: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error checking EKS cluster status')
        };
    }
};
