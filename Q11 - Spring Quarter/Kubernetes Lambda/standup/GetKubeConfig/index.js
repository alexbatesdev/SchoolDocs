// This code was written with the help of an AI assistant.
import { EKSClient, DescribeClusterCommand } from "@aws-sdk/client-eks";

const client = new EKSClient({ region: "us-west-2" });

export const handler = async (event) => {
    const clusterName = "Gezora-Godzooky-Cluster";

    const getKubeconfig = async (clusterName) => {
        try {
            const command = new DescribeClusterCommand({ name: clusterName });
            const response = await client.send(command);
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

    try {
        const kubeconfig = await getKubeconfig(clusterName);
        return {
            statusCode: 200,
            body: JSON.stringify({ kubeconfig })
        };
    } catch (error) {
        console.error(`Error in getKubeconfig handler: ${error}`);
        return {
            statusCode: 500,
            body: JSON.stringify('Error getting kubeconfig')
        };
    }
};
