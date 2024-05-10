# Conditions and if statements

# Python supports the usual logical conditions from mathematics:
'''
Equals: a == b
Not Equals: a != b
Less than: a < b
Less than or equal to: a <= b
Greater than: a > b
Greater than or equal to: a >= b
'''

# The if statement
# --------------------------
a = 1
b = 2
if a < b:
    print(f"{a} is less than {b}")

# Indentation
# Python relies on indentation (whitespace at the beginning of a line) 
# to define scope in the code. 
# Other programming languages often use curly-brackets for this purpose.

# the same if statement without indentation will raise an error
'''

if a < b:
print(f"{a} is less than {b}")

'''
# would raise an error


# The elif statement
# The elif keyword is pythons way of saying 
# "if the previous conditions was not true, then try this condition".
# --------------------------
a = 33
b = 20
if a < b:
    print(f"{a} is less than {b}")
elif a > b:
    print(f"{a} is greater than {b}")


# The else statement
# The else keyword catches anything which isn't caught by the preceding conditions.
# --------------------------
a = 40
b = 40
if a < b:
    print(f"{a} is less than {b}")
elif a > b:
    print(f"{a} is greater than {b}")
else:
    print(f"{a} must be equal to {b}")


# The ternary statement
# The else keyword catches anything which isn't caught by the preceding conditions.
# --------------------------
a = 40
b = 41
print(f"{a} is less than {b}") if a < b else print(f"{a} is not less than {b}")

# The ternary assignment statement
min = a if a < b else b
print(f"the smaller value is {min}")

# Logical operators
# --------------------------

# The and keyword is a logical operator, and is used to combine conditional statements
a, b, c = 200, 33, 500
if a > b and c > a:
    print("Both conditions are true")


# The or keyword is a logical operator, and is used to combine conditional statements
a, b, c = 200, 33, 500
if a > b or a > c:
  print("At least one of the conditions is True")


# Nested if statements
# --------------------------
x = 41
if x > 10:
  print("Above ten,")
  if x > 20:
    print("and also above 20!")
  else:
    print("but not above 20.")


# The pass statement
# if statements cannot be empty, but if you for some reason have an if statement with no content, 
# put in the pass statement to avoid getting an error.
# --------------------------
a = 33
b = 200
if b > a:
  pass