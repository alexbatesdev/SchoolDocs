# Initialize the stack
# Iterate over the tokens
# 7
# Set the pin as output
# blink(7)
from data_structures import InstructionList, BlinkInstruction, InitializeStackInstruction

def main():
    path = "input.txt"
    path_input = input(f"Enter the path of the input file (./{path}): ")
    if path_input != "":
        path = path_input

    instructions = InstructionList()
    instructions.append(InitializeStackInstruction())

    with open(path, "r") as input_file:
        for line in input_file:
            if line == "\n":
                continue
            count = int(line)
            instructions.append(BlinkInstruction([count]))

    path = "assembly.txt"
    path_output = input(f"Enter the output path of the file (./{path}): ")
    if path_output != "":
        path = path_output

    with open(path, "wb") as output_file:
        assembly_code = instructions.toAssembly()
        valid_input = False
        while not valid_input:
            user_input = input("Write to file? (Y/N) ")
            if user_input.lower() == "n":
                valid_input = True
                print("Write aborted. Exiting...")
                return
            elif user_input.lower() == "y":
                for line in assembly_code:
                    output_file.write((line + '\n').encode())
                output_file.close()
                valid_input = True
                print(f"File written to {path}")
            else:
                print("Invalid input")
                continue


if __name__ == "__main__":
    main()


            