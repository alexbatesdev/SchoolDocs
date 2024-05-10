from pydantic import BaseModel
from typing import Optional


class Edge(BaseModel):
    weight: int
    node_id_from: str
    node_id_to: str


class Node(BaseModel):
    id: str
    value: Optional[str]  # I think this is unnecessary, but I'm not sure
    edges: Optional[list[Edge]]


class MyGraph(BaseModel):
    nodes: dict[str, Node]

    def create_minimum_spanning_tree(self, start_id):
        print("Minimum spanning tree:")
        print("-------------------------------------------------")
        start_node = self.nodes[start_id]
        visited_node_ids = [start_node.id]
        possible_edges = start_node.edges
        total_distance = 0
        # look at edges on current node (that do not lead to another node)
        # pick node with smallest weight
        while len(visited_node_ids) != len(self.nodes):
            lightest_edge = None
            for edge in possible_edges:
                if edge.node_id_to not in visited_node_ids:
                    if lightest_edge == None or edge.weight < lightest_edge.weight:
                        lightest_edge = edge
            visited_node_ids.append(lightest_edge.node_id_to)
            total_distance += lightest_edge.weight
            # add edges from newly added node to possible_edges
            # but also remove any edges that lead to already visited nodes
            possible_edges += self.nodes[lightest_edge.node_id_to].edges

            def prune_possible_edges(possible_edges_in: list):
                possible_edges_out = []
                for edge in possible_edges_in:
                    if edge.node_id_to not in visited_node_ids:
                        possible_edges_out.append(edge)
                return possible_edges_out

            possible_edges = prune_possible_edges(possible_edges)
        print("Total distance:", total_distance)
        print("Visited Nodes:", visited_node_ids)

    @classmethod
    def create_graph(cls, instructions: list[str]):
        maze_line_index = 0  # This is how many lines into a specific maze we are, this will reset inbetween mazes. We know we have hit the end of a maze because we will have a line that is empty
        maze_nodes = {}

        for instruction in instructions:
            instruction = instruction.strip()
            instruction = instruction.split(",")
            # If we are on the first line of a maze, we know that we are defining the nodes
            if maze_line_index == 0:
                for node in instruction:
                    maze_nodes[node] = Node(id=node, value=None, edges=[])
                # print("Nodes:", maze_nodes)
            elif maze_line_index > 0:
                # print(instruction[0], "connects to", instruction[1:])
                for node in instruction[1:]:
                    # split the node into the node id and the weight
                    node = node.split(":")
                    # weight is after the colon, node id is before
                    maze_nodes[instruction[0]].edges.append(
                        Edge(
                            weight=node[1],  # Change me!
                            node_id_from=instruction[0],  # Change me!
                            node_id_to=node[0],
                        )
                    )

            maze_line_index += 1
        # print("-------------------------------------------------")
        # for node in maze_nodes:
        # print(node, maze_nodes[node])
        # print("-------------------------------------------------")
        return cls(nodes=maze_nodes)


if __name__ == "__main__":
    print("This is the main function")
    print("Enter file path:")
    # file_path = input("> ")
    file_path = "./NetworkInputTestB.txt"
    maze_count = 0
    maze_line_index = 0
    instructions = []

    with open(file_path, "r") as f:
        lines = f.readlines()
        for line in lines:
            line = line.strip()

            if line[:2] == "//":
                continue

            if maze_line_index == 0:
                instructions.append([])
                maze_count += 1

            if line == "":
                maze_line_index = 0
                continue
            instructions[maze_count - 1].append(line)
            maze_line_index += 1
    # print(instructions)

    index = 0
    for maze in instructions:
        graph = MyGraph.create_graph(maze)
        keys = []
        for node in graph.nodes:
            keys.append(node)
            print(graph.nodes[node].id, graph.nodes[node].edges)
        graph.create_minimum_spanning_tree(keys[0])
        index += 1
