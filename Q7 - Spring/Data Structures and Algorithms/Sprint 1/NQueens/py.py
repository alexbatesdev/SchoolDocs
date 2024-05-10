from pydantic import BaseModel
import random


class Queen(BaseModel):
    row: int
    col: int


class Board(BaseModel):
    id: int  # Incremental
    queens: list[Queen] = []
    steps: int = 0
    size_n: int

    _next_id = 1

    def __init__(self, n, **kwargs):
        id = Board._next_id
        Board._next_id += 1
        super().__init__(id=id, size_n=n)

    def printSolution(self):
        print("---------------")
        print(f"Solution {self.id}:")
        print(f"Steps: {self.steps}")
        print("---------------")
        for row in range(self.size_n):
            for col in range(self.size_n):
                drewQueen = False
                for queen in self.queens:
                    if queen.row == row and queen.col == col:
                        print("[Q]", end=" ")
                        drewQueen = True
                if not drewQueen:
                    print("[_]", end=" ")
            print("")

    def previewSolution(self, row_in, col_in):
        for row in range(self.size_n):
            for col in range(self.size_n):
                if row_in == row and col_in == col:
                    print("[X]", end=" ")
                    continue
                drewQueen = False
                for queen in self.queens:
                    if queen.row == row and queen.col == col:
                        print("[Q]", end=" ")
                        drewQueen = True
                if not drewQueen:
                    print("[_]", end=" ")
            print("")


def calculateNQueens(gridSize: int):
    """Calls the recursive method"""
    outputArray = [Board(gridSize)]
    print("Loading...")
    nQueens(0, gridSize, outputArray)
    for element in outputArray:
        if len(element.queens) == 0:
            outputArray.remove(element)
    return outputArray


def nQueens(row: int, gridSize: int, outputArray: list):
    """Actual recursive function"""
    if row == gridSize:
        board: Board = outputArray[len(outputArray) - 1]
        newBoard = board.copy()
        newBoard.id = board.id + 1
        newBoard.queens = board.queens[:-1]
        outputArray.append(newBoard)
        return True
    anySafeOptions = False
    for col in range(gridSize):
        if isSafe(row, col, outputArray[len(outputArray) - 1]):
            board: Board = outputArray[len(outputArray) - 1]
            board.queens.append(Queen(row=row, col=col))
            board.steps += 1
            anySafeOptions = True
            nQueens(row + 1, gridSize, outputArray)
    if not anySafeOptions:
        board: Board = outputArray[len(outputArray) - 1]
        board.queens.remove(board.queens[len(board.queens) - 1])
        return False
    if row == 0:
        print("END OF SEARCH")
        print("---------------")
        return True
    board: Board = outputArray[len(outputArray) - 1]
    board.queens.remove(board.queens[len(board.queens) - 1])
    return True


def isSafe(row: int, col: int, board: Board):
    """Checks if this slot is safe"""
    safe = True
    for queen in board.queens:
        if queen.row == row:
            safe = False
        if queen.col == col:
            safe = False
        colDiff = abs(col - queen.col)
        rowDiff = abs(row - queen.row)
        if colDiff == rowDiff:
            safe = False
    if board.size_n < 8:
        """pass"""
        board.previewSolution(row, col)
        print("---------------")
        # input(">")

    return safe


print("N Queens")
print("----------------------------")
print("Hey! This program is going to find every solution to the N Queens problem!")
print("----------------------------")
print("Input integer value for N")
try:
    input_out = int(input(">"))
except:
    print("Oi that's not a number, try again from the start")
    exit()
if input_out <= 0:
    print("Oi, that's an invalid number, try again from the start")
    exit()
output = calculateNQueens(input_out)

if len(output) == 0:
    print("---------------")
    print("No Solutions Found")
    print("---------------")
else:
    # totalSteps = 0
    print(f"Total Solutions: {len(output)}")
    print(f"Total Steps: {output[-1].steps}")
    print(f"Value of N: {output[0].size_n}")
    for solution in output:
        solution.printSolution()
    print("---------------")
    print(f"Total Solutions: {len(output)}")
    print(f"Total Steps: {output[-1].steps}")
    print(f"Value of N: {output[0].size_n}")
