# Helper Functions
def resolve_condition(condition: str):
    match condition:
        case "AL":  # Always
            return "1110"
        case "EQ":
            return "0000"
        case "NE":
            return "0001"
        case "CS":
            return "0010"
        case "CC":
            return "0011"
        case "MI":
            return "0100"
        case "PL":  # Positive or zero
            return "0101"
        case "VS":
            return "0110"
        case "VC":
            return "0111"
        case "HI":
            return "1000"
        case "LS":
            return "1001"
        case "GE":
            return "1010"
        case "LT":
            return "1011"
        case "GT":
            return "1100"
        case "LE":
            return "1101"
        case _:
            raise Exception("Invalid condition")


def resolve_register(register: str):
    return (bin(int(register.replace("R", "")))).replace("0b", "").zfill(4)


def strip_parenthesis(string: str):
    return string.replace("(", "").replace(")", "")


def reverse_list(list: list):
    return list[::-1]


# Assembler commands


def Comment(args: list):
    print("Comment: " + " ".join(args))
    return


def MOVW(args: list):
    condition = args[0]
    register = resolve_register(args[1])
    value = args[2]
    if value[0:2] == "0x":
        value = value[2:]
    binary_value = bin(int(value, 16)).replace("0b", "").zfill(16)
    imm4 = binary_value[0:4]
    imm12 = binary_value[4:]
    output = [
        (resolve_condition(condition) + "0011"),
        "0000" + imm4,
        register + imm12[0:4],
        imm12[4:12],
    ]
    return output


def MOVT(args: list):
    condition = args[0]
    register = resolve_register(args[1])
    value = args[2]
    if value[0:2] == "0x":
        value = value[2:]
    binary_value = bin(int(value, 16)).replace("0b", "").zfill(16)
    imm4 = binary_value[0:4]
    imm12 = binary_value[4:]
    output = [
        (resolve_condition(condition) + "0011"),
        "0100" + imm4,
        register + imm12[0:4],
        imm12[4:14],
    ]
    return output


def SingleDataProcess(opCode: str, args: list):
    condition = resolve_condition(args[0])
    special = "0"
    if args[1] == "S":
        special = "1"
        register = resolve_register(args[2])
        register2 = resolve_register(args[3])
        value = args[4]
    else:
        register = resolve_register(args[1])
        register2 = resolve_register(args[2])
        value = args[3]

    immediate = "0"
    if value[0:2] == "0x":
        value = value[2:]
        immediate = "1"
    elif value[0] == "R":
        immediate = "0"
        value = value.replace("R", "")
    else:
        value = value
        immediate = "1"

    binary_value = bin(int(value, 16)).replace("0b", "").zfill(12)
    output = [
        (condition + "00" + immediate + opCode[0]),
        (opCode[1:] + special + register2),
        register + binary_value[0:4],
        binary_value[4:12],
    ]
    return output


def ADD(args: list):
    return SingleDataProcess("0100", args)


def SingleDataTransfer(l: str, args: list):
    condition = resolve_condition(args[0])
    register = resolve_register(args[1])
    value = resolve_register(strip_parenthesis(args[2]))
    immediate = "0"
    if value[0:2] == "0x":
        value = value[2:]
        immediate = "1"
    elif value[0] == "R":
        immediate = "0"
        value = value.replace("R", "")

    # Values for the future
    p = "0"
    u = "0"
    b = "0"
    w = "0"
    offset = "000000000000"

    output = [
        (condition + "01" + immediate + p),
        (u + b + w + l + value),
        (register + offset[0:4]),
        (offset[4:12]),
    ]
    return output


def LDR(args: list):
    return SingleDataTransfer("1", args)


def ORR(args: list):
    return SingleDataProcess("1100", args)


def STR(args: list):
    return SingleDataTransfer("0", args)


def SUB(args: list):
    return SingleDataProcess("0010", args)


def Branch(link: str, args: list):
    condition = resolve_condition(args[0])
    offset = bin(int(args[1], 16)).replace("0b", "").zfill(24)
    output = [
        (condition + "101" + link),
        offset[0:8],
        offset[8:16],
        offset[16:24],
    ]
    return output


def B(args: list):
    return Branch("0", args)


def BL(args: list):
    return Branch("1", args)


# Return None, or a list of binary values in 2 byte chunks
methods = {
    "^^": Comment,
    "MOVW": MOVW,
    "MOVT": MOVT,
    "ADD": ADD,
    "LDR": LDR,
    "ORR": ORR,
    "STR": STR,
    "SUB": SUB,
    "B": B,
    "BL": BL,
}

output = []

path = "input.txt"
# path_input = input("Enter the path of the input file (./input.txt): ")
# if path_input != "":
#     path = path_input

with open(path, "r") as input_file:
    for line in input_file:
        # print(line.replace("\n", ""))
        split_line = (line.replace(",", "")).split()
        # print(split_line)
        command = split_line[0]
        # print(command)
        args = split_line[1:]
        # print(args)
        try:
            temp_data = methods[command](args)
            # print(command, args)
            if temp_data is not None:
                if type(temp_data) == list:
                    for i in temp_data[::-1]:
                        output.append(i)
                    print(" ".join(temp_data))
                else:
                    print(temp_data)
        except KeyError:
            print("Command not found")
            continue
        except Exception as e:
            print(e)
            continue

path = "output.txt"
path_output = input("Enter the output path of the file (./output.txt): ")
if path_output != "":
    path = path_output

with open(path, "wb") as output_file:
    binary_values = output
    byte_array = bytes([int(b, 2) for b in binary_values])
    output_file.write(byte_array)
    output_file.close()
