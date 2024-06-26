// This code was written with the help of an AI assistant.
import { EKSClient, CreateClusterCommand, DescribeAddonVersionsCommand } from "@aws-sdk/client-eks";

const client = new EKSClient({ region: "us-west-2" });


const getLatestEKSVersion = async () => {
    try {
        const command = new DescribeAddonVersionsCommand({});
        const response = await client.send(command);
        const versions = response.addons[0].addonVersions.sort((a, b) => b.addonVersion.localeCompare(a.addonVersion));
        const latestVersion = versions[0].addonVersion;
        return latestVersion;
    } catch (error) {
        console.error(`Error fetching EKS versions: ${error}`);
        throw error;
    }
};

export const handler = async (event) => {
    const clusterName = "Gezora-Godzooky-Cluster";
    // Replace with actual values
    const roleArn = "arn:aws:iam::993145419620:role/GezoraGodzookyClusterRole";
    const subnets = ["subnet-069098c52661689ad", "subnet-05eefc1475cc6fc33"];
    const securityGroup = "sg-0f2c02b7994192540";
    // Get the latest EKS version via a method in the main index.js file
    const version = await getLatestEKSVersion();

    const command = new CreateClusterCommand({
        name: clusterName,
        version: version,
        roleArn: roleArn,
        resourcesVpcConfig: {
            subnetIds: subnets,
            securityGroupIds: [securityGroup]
        }
    });

    try {
        const response = await client.send(command);

        return {
            statusCode: 200,
            body: JSON.stringify(response)
        };
    } catch (error) {
        console.error(`Error creating EKS cluster: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error creating EKS cluster')
        };
    }
};
