# Tuples

# Tuples are used to store multiple items in a single variable.

# A tuple is a collection which is ordered, unchangeable and allow duplicate values
# --------------------------
tuple1 = ("apple", "banana", "cherry")
print(tuple1)

# You can create a tuple without parenthesis as well
tuple2 = 1, 2, "Bob", True
print(tuple2)

# Using the tuple constructor
# --------------------------
tuple3 = tuple(('red', 'blue'))
print(tuple3)

# Tuple with only one item
# --------------------------
tuple4 = ('No') # this is not a tuple
print(tuple4)

tuple5 = ('Yes',) # this is a tuple when it has a trailing comma
print(tuple5)

print(len(tuple1))      # length of a tuple

# Use tuples to unpack multiple values
# --------------------------
v1, v2, v3 = ('Scott', 25, True)
print(v1, v2, v3)

