# Dictionaries

# Dictionaries are used to store data values in key:value pairs.


# A dictionary is a collection which is ordered, changeable and does not allow duplicates.
# Dictionary items are presented in key:value pairs and can be referred to using the key name
# --------------------------
dict1 = {
    'brand': 'Ford',
    'model': 'Mustang',
    'year': 1964
}
print(dict1)
print(dict1['brand'])   # Print the value of a specific key
print(dict1['model'])   

# change the value of a key
dict1['model'] = 'Thunderbird'
print(dict1['model'])   

#Duplicate keys are not permitted, the last value will overwrite the existing values
dict2 = {
    'street': '13 Main Street',
    'city': 'Kearns',
    'city': 'Sandy'     
}
print(dict2)

print(len(dict2))      # length of a dictionary


# Using the dict constructor
# --------------------------
dict3 = dict()
dict3['name'] = 'Bob'
dict3['age'] = 25
print(dict3)

# Dict can hold list, tuples, sets, and dictionaries
# --------------------------
dict4 = {
    'colors': ['red', 'white', 'blue'],
    'point': (10,21),
    'ages': {10, 20, 22, 41},
    'cars': {'make': 'Dodge', 'model': 'Charger'}
}
print(dict4)

# Numbers as keys
# --------------------------
dict5 = {
    1: 'zombie',
    2: 'vampire',
    3: 'werewolf',
    4: 'ghost'
}
print(dict5)
