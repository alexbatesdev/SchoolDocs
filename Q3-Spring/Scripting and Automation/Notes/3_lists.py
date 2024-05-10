# Lists

# Used to store multiple items as a collection
# of data in a single variable

# List items are ordered, changeable, and allow duplicate values
# list indexes are zero based meaning they start at 0 not 1
# --------------------------

mylist = ["apple", "banana", "cherry", "peach", "banana"]
print(mylist)
 
various = [1, 2, True, 'Bob', 1.25]
print(various)

print(mylist[2])        # element of a list
print(mylist[:1])       # range  of a list
print(mylist[1:3])       
print(mylist[::-1])     # reverse a list

various.append('New')   # add to a list
print(various)

various.pop()           # remove last item from list
print(various)

various.pop(1)           # remove specific index from list
print(various)

mylist.remove('cherry')    # remove specific item from list
print(mylist)

del mylist[0]           # remove specific index from list
print(mylist)

print(len(mylist))      # length of a list

mylist[0] = "berry"
print(mylist)

# Using the list constructor
# --------------------------
thislist = list()
thislist.append('Red')
thislist.append('White')
thislist.append('Blue')
print(thislist)

# Searching a list
# --------------------------
print(mylist.index("banana"))   # Find location of value in a list
print(mylist.count("banana"))   # Count occurences of value in a list
print("banana" in mylist)       # Is a value in the list

# Copying a list
# --------------------------
# lists default copy by reference
newlist = mylist
print(mylist)
print(newlist)

mylist.append("grape")
print(mylist)
print(newlist)

# use copy command to get an actual copy
copylist = mylist.copy()
mylist.append("pineapple")
print(mylist)
print(copylist)

# list comprehension
# https://www.w3schools.com/python/python_lists_comprehension.asp#