from py2neo import Graph, Node, Relationship, NodeMatcher, RelationshipMatcher
from py2neo.bulk import create_nodes, create_relationships

graph = Graph("bolt://localhost:7687", auth=("neo4j", "newPass"))
# results = graph.run("MATCH (n) return n")
# print(results)

def insertPerson(id, fname, lname, hireYear):
    person = Node("Person", id=id, fname=fname, lname=lname, hireYear=hireYear)
    graph.create(person)
    return person

def getPerson(id):
    matcher = NodeMatcher(graph)
    return matcher.match("Person", id=id).first()

def setPerson(id, fname, lname, hireYear):
    person = getPerson(id)
    person.update(fname=fname, lname=lname, hireYear=hireYear)
    graph.push(person)

def deletePerson(id):
    person = getPerson(id)
    graph.delete(person)

def insertRelationship(id1, id2, relationship):
    person1 = getPerson(id1)
    person2 = getPerson(id2)
    graph.create(Relationship(person1, relationship, person2))

def getRelationship(id1, id2):
    person1 = getPerson(id1)
    person2 = getPerson(id2)
    matcher = RelationshipMatcher(graph)
    return matcher.match((person1, person2)).first()

def setRelationship(id1, id2, relationship):
    person1 = getPerson(id1)
    person2 = getPerson(id2)
    rel = getRelationship(person1, person2)
    rel.update(relationship=relationship)
    graph.push(rel)

def deleteRelationship(id1, id2):
    person1 = getPerson(id1)
    person2 = getPerson(id2)
    rel = getRelationship(person1, person2)
    graph.delete(rel)

def runCypher():
    print("Enter Cypher query:")
    cypher = userInput = input('>')
    results = graph.run(cypher)
    return results
    

#TODO: add a function to add an index
#TODO: add a function to add a constraint

# testing
# insertPerson("10004", "Jeph", "Phriday", "2003")
# print(getPerson("10004"))
# setPerson('10004', "Squark", "Jeeble", "1776")
# deletePerson("10004")
# insertPerson("10004", "Gurt", "Chewsday", "2022")
# insertRelationship(getPerson("10003"), getPerson("10002"), "loves")
# print(getRelationship(getPerson("10003"), getPerson("10002")))
# insertRelationship(getPerson("10002"), getPerson("10003"), "hates")
# setRelationship(getPerson("10003"), getPerson("10002"), "dislikes")
# deleteRelationship(getPerson("10003"), getPerson("10002"))
# deleteRelationship(getPerson("10002"), getPerson("10003"))

def help():
    print("help - displays this message")
    print("exit - exits the program")
    print("----------------------------------------------------")
    print("C - create person <id> <fname> <lname> <hireYear>")
    print("R - get person <id>")
    print("U - set person <id> <fname> <lname> <hireYear>")
    print("D - delete person <id>")
    print("----------------------------------------------------")
    print("C - create relationship <id1> <id2> <relationship>")
    print("R - get relationship <id1> <id2>")
    print("U - set relationship <id1> <id2> <relationship>")
    print("D - delete relationship <id1> <id2>")
    print("----------------------------------------------------")
    print("cypher - asks for second input, runs cypher query with input")
    pass # Print all commands

def command(input):
    if (input[0] == "help"):
        help()
    
    if (input[0] == "create"):
        if (input[1] == "person"):
            # id, fname, lname, hireYear
            insertPerson(input[2], input[3], input[4], input[5])
        elif (input[1] == "relationship"):
            # id1, id2, relationship
            insertRelationship(input[2], input[3], input[4])
    
    if (input[0] == "get"):
        if (input[1] == "person"):
            # id
            print(getPerson(input[2]))
        elif (input[1] == "relationship"):
            # id1, id2
            print(getRelationship(input[2], input[3]))
            
    if (input[0] == "set"):
        if (input[1] == "person"):
            # id, fname, lname, hireYear
            setPerson(input[2], input[3], input[4], input[5])
        elif (input[1] == "relationship"):
            # id1, id2, relationship
            setRelationship(input[2], input[3], input[4])
            
    if (input[0] == "delete"):
        if (input[1] == "person"):
            # id
            deletePerson(input[2])
        elif (input[1] == "relationship"):
            # id1, id2
            deleteRelationship(input[2], input[3])
            
    if (input[0] == "cypher"):
        print(runCypher())
            
        


help()
playing = True
while (playing):
    
    userInput = '' 
    while userInput == '': 
        userInput = input('>')
        
    if (userInput == "exit"): break
    command(userInput.split(" "))