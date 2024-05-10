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

    def solve_maze(self, start, end):
        table = self.create_distance_table(start)
        output = []
        print("Distance table:")
        print("-------------------------------------------------")
        for i in table:
            print(
                "Node:",
                i,
                "|",
                "Distance:",
                table[i]["weight"] if str(table[i]["weight"]) != "inf" else "-",
                "|",
                "Previous:",
                table[i]["previous"] if table[i]["previous"] is not None else "-",
                "|",
                "Visited:",
                table[i]["visited"],
            )
        current_node_id = end
        while current_node_id is not start:
            output.append(current_node_id)
            current_node_id = table[current_node_id]["previous"]
            if current_node_id is None:
                return "No path found"
        output.append(start)
        output = output[::-1]
        return output

    def create_distance_table(self, start):
        distance_table = {}
        for node in self.nodes:
            distance_table[node] = {
                "weight": float("inf"),
                "previous": None,
                "visited": False,
            }
        distance_table[start]["weight"] = 0

        def checkAllVisited():
            allVisited = True
            for node in distance_table:
                if distance_table[node]["visited"] == False:
                    allVisited = False
            return allVisited

        while not checkAllVisited():
            min_weight = float("inf")
            for node in distance_table:
                if (
                    distance_table[node]["weight"] <= min_weight
                    and distance_table[node]["visited"] == False
                ):
                    min_weight = distance_table[node]["weight"]
                    current_node = self.nodes[node]

            for edge in current_node.edges:
                if (
                    distance_table[edge.node_id_to]["visited"] == False
                    and distance_table[edge.node_id_to]["weight"]
                    > edge.weight + distance_table[current_node.id]["weight"]
                ):
                    distance_table[edge.node_id_to]["previous"] = current_node.id
                    distance_table[edge.node_id_to]["weight"] = (
                        edge.weight
                        + distance_table[distance_table[edge.node_id_to]["previous"]][
                            "weight"
                        ]
                    )
            distance_table[current_node.id]["visited"] = True

        return distance_table

    @classmethod
    def create_graph(cls, instructions: list[str]):
        maze_line_index = 0  # This is how many lines into a specific maze we are, this will reset inbetween mazes. We know we have hit the end of a maze because we will have a line that is empty
        maze_nodes = {}

        for instruction in instructions:
            instruction = instruction.strip()
            instruction = instruction.split(",")
            # print(maze_line_index, line)
            # If we are on the first line of a maze, we know that we are defining the nodes
            if maze_line_index == 0:
                for node in instruction:
                    maze_nodes[node] = Node(id=node, value=None, edges=[])
                # print("Nodes:", maze_nodes)
            # If we are passed the first 2 lines, we know that we are defining the edges
            # Right now edges are being made twice, once for each node, this is not ideal I don't think. But also this would make it quite easy to define a directed graph
            elif maze_line_index > 1:
                # print(instruction[0], "connects to", instruction[1:])
                for node in instruction[1:]:
                    maze_nodes[instruction[0]].edges.append(
                        Edge(weight=1, node_id_from=instruction[0], node_id_to=node)
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
    file_path = "./TestMazes.txt"
    maze_count = 0
    maze_line_index = 0
    instructions = []
    path_nodes = []

    with open(file_path, "r") as f:
        lines = f.readlines()
        for line in lines:
            line = line.strip()

            if line[:2] == "//":
                continue

            if maze_line_index == 0:
                instructions.append([])
                maze_count += 1
            if maze_line_index == 1:
                path_nodes.append(line.split(","))

            if line == "":
                maze_line_index = 0
                continue
            instructions[maze_count - 1].append(line)
            maze_line_index += 1
    # print(instructions)

    index = 0
    for maze in instructions:
        graph = MyGraph.create_graph(maze)
        # for node in graph.nodes:
        #     print(graph.nodes[node].id, graph.nodes[node].edges)
        # print(path_nodes[index])
        print("-------------------------------------------------")
        solved = graph.solve_maze(path_nodes[index][0], path_nodes[index][1])
        print("-------------------------------------------------")
        print(
            "Calculate path between:",
            path_nodes[index][0] + " -> " + path_nodes[index][1],
        )
        print("Path:", solved)
        index += 1
