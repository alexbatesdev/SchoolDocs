# Introduction to Python Basics

# Python Variables
# --------------------------
# No variable type declaration, variable is created when you assign it
# Basic types: Int, Float, String (single or double quotes), Boolean
# Strings can be represented using single or double quotes

1
1.0
"double quotes"
'single quotes'
True
False

# Variables are case sensitive e.g. Name <> name
# Begin with letter or _ and may contain letters, numbers and underscore
# Conventions says to use Snake Case naming e.g. my_variable_name = "John"

a = 3
b = 4.0
c = 'abcde'
d = True

# You can assign multiple variables to different values at once
x, y, z = 1, 'Two', 3.0

# You can assign multiple variables to the same value at once
u = v = w = 'Same'

# Variables can be changed after first assigned
f = 1
f = 'change f to a string'

# Printing to the console
# --------------------------
# Use print()
# You can print variables, literals, objects

print(a)
print(b)
print(c)
print(d)
print('Hello World')

# You can print multiple things at once using a comma splice
print(x, y, z)

# You can specify what is printed at the end of the print statement
print(a, end=' [[Print this at the end]]')
print()
print(a, end='\n')

# Comments
# --------------------------
# Use the hashtag to create a single comment e.g. #
# Use triple quotes to create a block comment e.g. """ """

# This is an example of a one line comment

""" 
    This is an example of a block commment
    that can span across multiple new lines
"""

# Casting variables
# --------------------------
# You can specify a variables type by casting e.g. int(), str(), float(), bool()

m = int(1)
n = str(1)
o = float(1)
p = bool(1)
q = bool('True')

print(m, n, o, p, q)

# Getting the variable type
# --------------------------
# Use type() to retrieve the type

r = 1
s = 1.0
t = '1'
u = True

print(type(r))
print(type(s))
print(type(t))
print(type(u))