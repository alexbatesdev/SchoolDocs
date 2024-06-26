// This code was written by an AI assistant

import { EKSClient, DescribeClusterCommand } from "@aws-sdk/client-eks";
import k8s from "@kubernetes/client-node";

const eks = new EKSClient({ region: "us-west-2" });

export const handler = async (event) => {
    const clusterName = process.env.CLUSTER_NAME; // Set this environment variable in your Lambda configuration

    try {
        // Describe the EKS cluster to get the cluster details
        const describeClusterCommand = new DescribeClusterCommand({ name: clusterName });
        const response = await eks.send(describeClusterCommand);

        if (!response.cluster) {
            throw new Error(`Cluster ${clusterName} not found`);
        }

        const cluster = response.cluster;

        // Load the Kubernetes configuration
        const clusterCert = Buffer.from(cluster.certificateAuthority.data, 'base64').toString('utf8');
        const clusterUrl = cluster.endpoint;

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
                            command: "aws-iam-authenticator",
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

        const kc = new k8s.KubeConfig();
        kc.loadFromString(JSON.stringify(kubeconfig));

        const k8sApi = kc.makeApiClient(k8s.CoreV1Api);

        // Apply Kubernetes resources
        const configMap = {
            apiVersion: "v1",
            kind: "ConfigMap",
            metadata: {
                name: "aws-auth",
                namespace: "kube-system",
            },
            data: {
                mapRoles: `
        - groups:
          - system:bootstrappers
          - system:nodes
          - system:node-proxier
          rolearn: arn:aws:iam::993145419620:role/FargatePodRole
          username: system:node:{{SessionName}}
        - groups:
          - system:masters
          rolearn: arn:aws:iam::993145419620:role/GezoraGodzookyLambda
          username: auto-role`,
                mapUsers: `
        - userarn: arn:aws:iam::993145419620:user/alex.bates
          username: alex.bates
          groups:
          - system:masters
        - userarn: arn:aws:iam::993145419620:user/optimus.prime
          username: optimus.prime
          groups:
          - system:masters`,
            },
        };

        await k8sApi.createNamespacedConfigMap("kube-system", configMap);

        return {
            statusCode: 200,
            body: JSON.stringify({ message: "ConfigMap applied successfully" }),
        };
    } catch (error) {
        console.error("Error applying ConfigMap:", error);
        return {
            statusCode: 500,
            body: JSON.stringify({ message: error.message }),
        };
    }
};