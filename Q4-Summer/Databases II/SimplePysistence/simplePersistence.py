import os
import pickle
import time

#pickle for serialization

class Employee:
    def __init__(self, id, fname, lname, hireYear):
        self.id = id
        self.fname = fname
        self.lname = lname
        self.hireYear = hireYear
    def __str__(self):
        return f"id: {self.id} First name: {self.fname} Last Name: {self.lname} Hire year: {self.hireYear}" 


def PrintPeopleDetails(path):
    files = os.listdir(path)
    for i in files:
        route = path + "\\" + i
        f = open(route, "r")
        print(f.read())
        f.close()


def PrintEmployees(path):
    files = os.listdir(path)
    employees = []
    for i in files:
        route = path + "\\" + i
        f = open(route, "r")
        info = f.read().split(", ")
        employees.append(Employee(info[0], info[1], info[2], info[3]))
        
        f.close()
        
    for employee in employees:
        print(employee)

def AddEmployee(id, fname, lname, hireDate):
    route = "Assignment 1 - data\people\long\\" + str(id) + ".txt"
    newFile = open(route, "w")
    data = f"{id}, {fname}, {lname}, {hireDate}"
    newFile.write(data)
    newFile.close()

def DeleteEmployee(id):
    route = "Assignment 1 - data\people\long\\" + str(id) + ".txt"
    newFile = os.remove(route)

def UpdateEmployee(id, fname, lname, hireDate):
    AddEmployee(id, fname, lname, hireDate)

def SerializeAllEmployees():
    files = os.listdir("Assignment 1 - data\people\long\\")
    for i in files:
        path = "Assignment 1 - data\people\long serialized\\" + i.split(".")[0] + ".pickle"
        with open(path, "wb") as out:
            with open("Assignment 1 - data\people\long\\" + i, "r") as _in:
                data = _in.read()
                pickle.dump(data, out)

def GetSerializedEmployee(id):
    path = "Assignment 1 - data\people\long serialized\\" + str(id) + ".pickle"
    with open(path, "rb") as out:
        data = pickle.load(out)
        info = data.split(", ")
        return Employee(info[0], info[1], info[2], info[3])

def FindEmployeeById(id):
    path = f"Assignment 1 - data\people\long\\{id}.txt"
    with open(path, "r") as _in:
        _in = _in.read().split(", ")
        return Employee(_in[0], _in[1], _in[2], _in[3])


def FindEmployeeByLastName(lastname):
    files = os.listdir("Assignment 1 - data\people\long\\")
    for i in files:
        path = f"Assignment 1 - data\people\long\\{i}"
        with open(path, "r") as employeeFile:
            employeeFile = employeeFile.read().split(", ")
            if (employeeFile[2] == lastname):
                return Employee(employeeFile[0], employeeFile[1], employeeFile[2], employeeFile[3])


def FindAllEmployeesByLastName(lastname):
    employees = []
    files = os.listdir("Assignment 1 - data\people\long\\")
    for i in files:
        path = f"Assignment 1 - data\people\long\\{i}"
        with open(path, "r") as employeeFile:
            employeeFile = employeeFile.read().split(", ")
            if (employeeFile[2] == lastname):
                employees.append(Employee(employeeFile[0], employeeFile[1], employeeFile[2], employeeFile[3]))
    return employees

def PrintSerializedDetails(path):
    for i in os.listdir(path):
        with open(path + i, "rb") as out:
            data = pickle.load(out)
            info = data.split(", ")
            print(Employee(info[0], info[1], info[2], info[3]))


def GetAllEmployees(path):
    empDict = {}
    for i in os.listdir(path):
        with open(path + i, "rb") as out:
            data = pickle.load(out)
            info = data.split(", ")
            empDict[info[0]] = Employee(info[0], info[1], info[2], info[3])
    return empDict


def PrintAllEmployees():
    dictionary = GetAllEmployees("Assignment 1 - data\people\long serialized\\")
    for i in dictionary:
        print(dictionary[i])
        
def SimonPrintAllEmployees():
    dictionary = GetAllEmployees("Assignment 1 - data\people\long serialized\\")
    for i in dictionary.values():
        print(i)


# PrintEmployees("Assignment 1 - data\people\simple")

# AddEmployee(10001, "Gyurt", "Benson", 2002)
# DeleteEmployee(10001)
# UpdateEmployee(10001, "Greeble", "Gonky", 2002)

# SerializeAllEmployees()
# print(GetSerializedEmployee(7))

# print(FindAllEmployeesByLastName("PITTS"))

# PrintSerializedDetails("Assignment 1 - data\people\long serialized\\")
# print(GetAllEmployees("Assignment 1 - data\people\long serialized\\"))
# PrintAllEmployees()

start = time.time()
FindEmployeeByLastName("FOX")
end = time.time()
print(end - start)