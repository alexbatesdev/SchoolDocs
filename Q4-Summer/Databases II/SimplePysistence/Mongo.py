from pymongo import MongoClient;
import os;

class Employee:
    def __init__(self, id, fname, lname, hireYear):
        self.id = id
        self.fname = fname
        self.lname = lname
        self.hireYear = hireYear
    def __str__(self):
        return f"id: {self.id} First name: {self.fname} Last Name: {self.lname} Hire year: {self.hireYear}" 

client = MongoClient("mongodb://localhost:2717/")
# select dbofmyown
db = client["dbofmyown"]
#select the collection collectionofmyown
collection = db["collectionofmyown"]
#insert the data
def InsertEmployee(employee):
    collection.insert_one(employee)



def PrintEmployees(path):
    files = os.listdir(path)
    employees = []
    for i in files:
        route = path + "/" + i
        f = open(route, "r")
        info = f.read().split(", ")
        empDict = {"id": info[0], "fname": info[1], "lname": info[2], "hireYear": info[3]}
        print(empDict)
        InsertEmployee(empDict)
        
        f.close()
        
    

# PrintEmployees("./Assignment 1 - data/people/long")

InsertEmployee({"id": 10002, "fname": "Boof", "lname": "Joofenburg", "hireYear": "2000"})