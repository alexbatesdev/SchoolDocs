# Loops

# Python has two primitive loop commands:
#   while loops
#   for loops

# The while loop
# --------------------------
# With the while loop we can execute a set of statements as long as a condition is true.

i = 1
while i < 6:
  print(i)
  i += 1

# Breaking a loop
# With the break statement we can stop the loop even if the while condition is true
i = 1
while i < 6:
  print(i)
  if i == 3:
    break
  i += 1

# Continuing in a loop
# With the continue statement we can stop the current iteration, and continue with the next
i = 0
while i < 6:
  i += 1
  if i == 3:
    continue
  print(i)

# Using the else statement
i = 1
while i < 6:
  print(i)
  i += 1
else:
  print("i is no longer less than 6")


# The for loop
# --------------------------
# A for loop is used for iterating over a sequence 
# (that is either a list, a tuple, a dictionary, a set, or a string).
# With the for loop we can execute a set of statements, 
# once for each item in a list, tuple, set etc.

fruits = ["apple", "banana", "cherry"]
for f in fruits:
  print(f)

for i in 'bananas':
    print(i)


# Iterating over a range
# To loop through a set of code a specified number of times, we can use the range() function,

for i in range(6):
  print(i)

for i in range(2, 6): # begin, end
  print(i)

for i in range(2, 6, 3): # begin, end, step
  print(i)


# Reverse direction
# --------------------------

for i in reversed(range(6)):
  print(i)



# Nested loops
# --------------------------

adj = ["red", "big", "tasty"]
fruits = ["apple", "banana", "cherry"]

for a in adj:
  for f in fruits:
    print(a, f)