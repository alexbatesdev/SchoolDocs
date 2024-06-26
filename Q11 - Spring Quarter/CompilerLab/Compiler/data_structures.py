class InstructionList:
    def __init__(self):
        self.head: Instruction = None
        self.tail: Instruction = None

    def append(self, command):
        if self.head is None:
            self.head = command
            self.tail = command
        else:
            self.tail.setNext(command)
            self.tail = command

    def toAssembly(self):
        assembly = []
        current = self.head
        while current is not None:
            current_assembly = current.toAssembly()
            assembly.extend(current_assembly)
            current = current.next
        
        return flatten_list(assembly)
        

    def getAtIndex(self, index):
        current = self.head
        for i in range(index):
            current = current.next
        return current


class Instruction:
    def __init__(self, args=[], label=None):
        self.args: list[str] = args
        self.label: str = label
        self.next: Instruction = None
        self.previous: Instruction = None

    def toAssembly(self):
        raise NotImplementedError("Subclasses should implement this method.")

    def appendLabel(self, instructions, label):
        if label is not None:
            instructions[0] = instructions[0][0] + f" :3 {label}"
            output = instructions
            return output
        else:
            return instructions

    def setNext(self, next):
        self.next = next
        next.previous = self

    def setPrevious(self, previous):
        self.previous = previous
        previous.next = self

    def __str__(self) -> str:
        return f"------------------------\n{self.__class__.__name__} {self.args}\n{self.toAssembly()}"


#  ---------------------------------------------------------------


class InitializeStackInstruction(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        instructions = ["MOVW R13, 0x0", "MOVT R13, 0x0"]
        instructions = self.appendLabel(instructions, self.label)
        return instructions


class BlinkInstruction(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        assembly = []
        blink_count = self.args[0]
        assembly.append(SetPinAsOutput([21]).toAssembly())
        assembly.append(TurnPinOn([21], label="start").toAssembly())
        assembly.append(Wait([1]).toAssembly())
        assembly.append(TurnPinOff([21]).toAssembly())
        assembly.append(Wait([1]).toAssembly())
        blink_count -= 1
        while blink_count > 0:
            assembly.append(TurnPinOn([21]).toAssembly())
            assembly.append(Wait([1]).toAssembly())
            assembly.append(TurnPinOff([21]).toAssembly())
            assembly.append(Wait([1]).toAssembly())
            blink_count -= 1
        assembly.append(BranchToLabel(["start"]).toAssembly())
        assembly = self.appendLabel(assembly, self.label)
        return assembly


class SetPinAsOutput(Instruction):
    def __init__(self, args=[], label=None):
        self.function_address = None
        self.pin_number = None
        super().__init__(args, label)

    def toAssembly(self):
        self.resolvePinNumber()
        assembly = []
        assembly.append(StoreRegisters().toAssembly())
        assembly.append(StoreToRegister(["3F200000", "R4"]).toAssembly())
        assembly.append(
            [
                f"ADD R5, R4, {self.function_address}",
                "LDR R3, (R5)",
                f"ORR R3, R3, {self.pin_number}",
                "STR R3, (R5)",
            ]
        )
        assembly.append(LoadRegisters().toAssembly())
        assembly = self.appendLabel(assembly, self.label)
        return assembly

    def resolvePinNumber(self):
        pin_number = self.args[0]
        binary_step = ""
        function_address_end = ""
        if pin_number < 0 or pin_number > 53:
            raise ValueError("Pin number must be between 0 and 53")
        elif pin_number < 10:
            function_address_end = "0x0"
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass
        elif pin_number < 20:
            function_address_end = "0x4"
            pin_number -= 10
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass
        elif pin_number < 30:
            function_address_end = "0x8"
            pin_number -= 20
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass
        elif pin_number < 40:
            function_address_end = "0xC"
            pin_number -= 30
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass
        elif pin_number < 50:
            function_address_end = "0x10"
            pin_number -= 40
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass
        else:
            function_address_end = "0x14"
            pin_number -= 50
            for i in range(10):
                if pin_number == i:
                    binary_step += "100"
                else:
                    binary_step += "000"
            pass

        binary_reversed = binary_step[::-1]
        integer_value = int(binary_reversed, 2)
        hex_value = hex(integer_value)
        self.pin_number = hex_value
        self.function_address = function_address_end


class TurnPinOn(Instruction):
    def __init__(self, args=[], label=None):
        self.pin_number = None
        self.function_address = None
        super().__init__(args, label)

    def toAssembly(self):
        self.resolvePinNumber()
        assembly = []
        assembly.append(StoreRegisters().toAssembly())
        assembly.append(StoreToRegister(["3F200000", "R4"]).toAssembly())
        assembly.append([f"ADD R3, R4, {self.function_address}"])
        assembly.append(StoreToRegister([f"{self.pin_number.replace("0x", "")}", "R5"]).toAssembly())
        assembly.append(["STR R5, (R3)"])
        assembly.append(LoadRegisters().toAssembly())
        assembly = self.appendLabel(assembly, self.label)
        return assembly

    def resolvePinNumber(self):
        pin_number = self.args[0]
        binary_step = ""
        if pin_number < 0 or pin_number > 53:
            raise ValueError("Pin number must be between 0 and 53")
        elif pin_number < 32:
            self.function_address = "0x1C"
        elif pin_number > 31:
            pin_number -= 32
            self.function_address = "0x20"

        for i in range(32):
            if pin_number == i:
                binary_step += "1"
            else:
                binary_step += "0"

        binary_reversed = binary_step[::-1]
        integer_value = int(binary_reversed, 2)
        hex_value = hex(integer_value)
        self.pin_number = hex_value


class TurnPinOff(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        self.resolvePinNumber()
        assembly = []
        assembly.append(StoreRegisters().toAssembly())
        assembly.append(StoreToRegister(["3F200000", "R4"]).toAssembly())
        assembly.append([f"ADD R3, R4, {self.function_address}"])
        assembly.append(StoreToRegister([f"{self.pin_number.replace("0x", "")}", "R5"]).toAssembly())
        assembly.append(["STR R5, (R3)"])
        assembly.append(LoadRegisters().toAssembly())
        assembly = self.appendLabel(assembly, self.label)
        return assembly

    def resolvePinNumber(self):
        pin_number = self.args[0]
        binary_step = ""
        if pin_number < 0 or pin_number > 53:
            raise ValueError("Pin number must be between 0 and 53")
        elif pin_number < 32:
            self.function_address = "0x28"
        elif pin_number > 31:
            pin_number -= 32
            self.function_address = "0x2C"

        for i in range(32):
            if pin_number == i:
                binary_step += "1"
            else:
                binary_step += "0"
        binary_reversed = binary_step[::-1]
        integer_value = int(binary_reversed, 2)
        hex_value = hex(integer_value)
        self.pin_number = hex_value


class Wait(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        assembly = []
        assembly.append(StoreRegisters().toAssembly())
        assembly.append(StoreToRegister(["3F4240", "R6"]).toAssembly())
        assembly.append(["SUB S R6, R6, 1", "BPL 0xFFFFFD"])
        assembly.append(LoadRegisters().toAssembly())
        assembly = self.appendLabel(assembly, self.label)
        return assembly


class LoadRegisters(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        assembly = ["LDMEA R13!, {R3-R12}"]
        assembly = self.appendLabel(assembly, self.label)
        return assembly


class StoreRegisters(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        assembly = ["STMEA R13!, {R3-R12}"]
        assembly = self.appendLabel(assembly, self.label)
        return assembly


class StoreToRegister(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        register = self.args[1]
        value = (self.args[0]).zfill(8)
        value_top = value[:4]
        value_bottom = value[4:]
        assembly = [
            f"MOVW {register}, 0x{value_bottom}",
            f"MOVT {register}, 0x{value_top}",
        ]
        assembly = self.appendLabel(assembly, self.label)
        return assembly


class BranchToLabel(Instruction):
    def __init__(self, args=[], label=None):
        super().__init__(args, label)

    def toAssembly(self):
        label = self.args[0]
        assembly = ["B :3c " + label]
        return assembly

def flatten_list(nested_list):
    flat_list = []
    for item in nested_list:
        if isinstance(item, list):
            flat_list.extend(flatten_list(item))
        else:
            flat_list.append(item)
    return flat_list

if __name__ == "__main__":
    instructions = InstructionList()
    instructions.append(InitializeStackInstruction())
    instructions.append(BlinkInstruction([3]))