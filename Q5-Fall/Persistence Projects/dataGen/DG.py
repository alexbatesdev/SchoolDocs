from pymongo import MongoClient

client = MongoClient('localhost', 27017) # connect to the server

for i in range(0, 1000):
    poy = i + " poy"
    db.test.insert({i: poy})

