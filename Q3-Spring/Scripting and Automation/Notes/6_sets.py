# Sets

# Sets are used to store multiple items in a single variable.

# A set is a collection which is both unordered and unindexed.
# --------------------------
set1 = {"apple", "banana", "cherry"}
print(set1)

# A set does not allow duplicate values
# --------------------------
set2 = {"apple", "banana", "cherry", "banana"}
print(set2)

print(len(set1))      # length of a set
print(len(set2))      

# A set can contain multiple types and other hashable types such as a tuple 
# this means it cannot contain a dict because it is not hashable
# Hashable means that its initial hash remains through its lifetime, doesn't change
# --------------------------
set3 = {
    "Bob",
    25,
    ("red", "white", "blue"),
}
print(set3)

# Using the set constructor, note we use the parenthesis and not the curly braces
# --------------------------
set4 = set((1,2,3,4,3,5,7,2,1))
print(set4)


# Using the set() constructor to make a set of unique characters
# --------------------------
set5 = set('Hello, my name is Bob')
print(set5)


# Union and Intersection of sets
# --------------------------
setA = set('1234567')
setB = set('12389')
setC = set('123')
print(setA.intersection(setB))
print(setA.union(setB))

print(setA.issuperset(setB))
print(setA.issuperset(setC))

print(setA.difference(setB))
print(setA-setB)