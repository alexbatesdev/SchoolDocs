# json and csv

# JSON stands for JavaScript Object Notation
# JSON is a lightweight format for storing and transporting data
# JSON is often used when data is sent from a server to a web page
# JSON is "self-describing" and easy to understand

# JSON Syntax Rules
# 	• Data is in name/value pairs
# 	• Data is separated by commas
# 	• Curly braces hold objects
# Square brackets hold arrays

# Python objects and their equivalent conversion to JSON:

# Python	    JSON Equivalent
# dict	        object
# list, tuple	array
# str	        string
# int, float	number
# True	        true
# False	        false
# None	        null

# This is an example of what Json looks like
# {
#     "employees":[
#         {"name":"Scott", "age":21},
#         {"name":"David", "age":34}
#     ]
# }

import json

# Parse a string as json
data = '{"name":"Scott","age":21,"type":"zombie"}'
print(data)

data_json = json.loads(data)    # use json.loads to convert a string to json

print(data_json)                # notice this is now json format
print(data_json['type'])        # grab a value using the key

string_json = json.dumps(data_json) # use json.dumps to convert json to a string
print(string_json)

print('-' * 50)

# An array of objects in json
names = [
    {
        "name":"Scott",
        "age":21
    },
    {
        "name":"Bob",
        "age": 55
    },
    {
        "name":"Sally",
        "age":17
    }
]

# Write json to a file
with open('file.txt', 'w') as file:
    file.write(json.dumps(names))

# Write nicely formatted json to a file
with open('file.txt', 'w') as file:
    file.write(json.dumps(names, indent=4))

# json.dump can write directly to a file
with open('file.txt', 'w') as file:
    json.dump(names, file, indent=4)

with open('file.txt', 'r') as file:
    all_names = json.loads(file.read())

print(all_names[1]['age'])

# json.load can deserialize a file itself and can accept a file object
with open('file.txt', 'r') as file:
    all_names = json.load(file)

print(all_names[1]['age'])