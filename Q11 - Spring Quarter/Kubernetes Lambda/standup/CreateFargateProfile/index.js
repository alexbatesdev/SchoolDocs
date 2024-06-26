// This code was written with the help of an AI assistant.
import { EKSClient, CreateFargateProfileCommand } from "@aws-sdk/client-eks";

const client = new EKSClient({ region: "us-west-2" });

export const handler = async (event) => {
    const params = {
        clusterName: "Gezora-Godzooky-Cluster",
        fargateProfileName: "myFargateProfile",
        podExecutionRoleArn: "arn:aws:iam::993145419620:role/YourFargatePodExecutionRole",
        selectors: [
            {
                namespace: "default",
                labels: {
                    "env": "prod"
                }
            }
        ],
        subnets: ["subnet-069098c52661689ad", "subnet-05eefc1475cc6fc33"]
    };

    const command = new CreateFargateProfileCommand(params);

    try {
        const response = await client.send(command);
        console.log("Fargate profile created successfully:", response);
        return response;
    } catch (error) {
        console.error("Error creating Fargate profile:", error);
        throw error;
    }
};
