# Functions

# A function is a block of code which only runs when it is called.
# You can pass data, known as parameters, into a function.
# A function can return data as a result.

# Creating a function
# --------------------------
# functions use the def keyword then the function name followed by a colon.
# note how the body of the function will be indented

def my_function():
  print("Hello from a function")

# Calling a function
# --------------------------
my_function()

# Return values
# --------------------------
def my_function_returns():
    return 'Thank you for calling this function'

print(my_function_returns())

# Arguments
# --------------------------
# Information can be passed into functions as arguments.
# Arguments are specified after the function name, inside the parentheses. 
# You can add as many arguments as you want, just separate them with a comma.

def my_function2(name): # single parameter
  print(f"Hi, my name is {name}")

my_function2("Bob")
my_function2("Suzi")

def my_function3(first, last):  # multiple parameters
    print(f'My name is {first} {last}')

my_function3('Bob', 'Thomas')

# Arbitrary arguments
# If the number of arguments is unknown, add a * before the parameter name
# --------------------------

def my_function4(*kids):
    print(f"I have {len(kids)} kids")
    print(f'Kid #2 is named {kids[1]}')

my_function4('Tom', 'Sally', 'Bruce')


# Keyword Arguments
# You can also send arguments with the key = value syntax.
# --------------------------
def my_function5(child3, child2, child1):
  print("The youngest child is " + child3)

my_function5(child1 = "Tom", child2 = "Sally", child3 = "Bruce")

# Arbitrary Keyword Arguments
# If you do not know how many keyword arguments that will be passed into your function, 
# add two asterisk: ** before the parameter name in the function definition.
# --------------------------

def my_function6(**kid):
  print(f"The youngest child is {kid['child3']}")

my_function5(child1 = "Tom", child2 = "Sally", child3 = "Bruce")

# Default Parameter Value
# If we call the function without argument, it uses the default value
# --------------------------

def my_function7(age = 25):
  print(f"I am {age} years old")

my_function7(20)
my_function7()

# Passing other types as an argument
# You can send any data types of argument to a function (string, number, list, dictionary etc.), 
# and it will be treated as the same data type inside the function.
# --------------------------
def my_function8(fruit):
  print(f"I love {len(fruit)} kinds of fruit.  They are {fruit}")

fruits = ["apple", "banana", "cherry"]

my_function8(fruits)


# Global variables
# Variables initialized outside of functions are by definition global
# You can read these variables inside functions
# However, if you want to change a global variable you must declare it a global
#   inside your function.  Otherwise, the function will consider it a local variable.
# Declare a variable as global using the global key word

outside = 100
def my_function9():
    global outside
    outside += 10
    
my_function9()
print(outside)


# Recursion
# Recursion is a common programming concept. It means that a function calls itself. 
# This has the benefit that you can loop through data to reach a result.
# --------------------------

loops = 0
def try_recursion(value):
    global loops
    loops += 1
    print(f'looping: {loops}')

    if value > 0:
        result = value + try_recursion(value - 1)
        print(result)
    else:
        result = 0
    return result

try_recursion(6)

