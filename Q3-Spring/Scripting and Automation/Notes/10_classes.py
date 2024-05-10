# Classes and Objects

from os import system
system('cls')           # neat trick to clear the screen when you execute the code


# Python is an object oriented programming language.
# Almost everything in Python is an object, with its properties and methods.
# A Class is like an object constructor, or a "blueprint" for creating objects.

# Creating a Class and an object
# --------------------------

class MyClass:
    this.prop = True

m1 = MyClass()  # an instance of the class creates and object
print(m1.prop)


# The __init__() function
# --------------------------
# All classes have a function called __init__(), which is the constructor of the class.
# This is always executed when the class is being initiated.
# Use the __init__() function to assign values to object properties, 
# or other operations that are necessary to do when the object is being created

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

p1 = Person("Fred", 25)
print(p1.name, p1.age)

# The self parameter is a reference to the current instance of the class, 
# and is used to access variables that belongs to the class.
# It does not have to be named self, you can call it whatever you like, 
# but it has to be the first parameter of any function in the class:


# Class methods
# --------------------------
# Classes can also contain methods. Methods in objects are functions that will belong to the object.

class Animal:
    def __init__(self, name, type):
        self.name = name
        self.type = type
        self.is_alive = True

    def introduce(self):
        print(f"Hello my {self.type}'s name is {self.name}")


a1 = Animal('Fido', 'dog')
a1.introduce()

a1.name = 'Rover'
a1.introduce()  # You can modify object properties

print(a1.is_alive)
del a1.is_alive # you can delete an object property
del a1          # you can delete an object



# Classes Inheritence and Polymorphism
# --------------------------
# Inheritance - allows us to define a class that inherits all the methods and properties from another class.
# Polymorphism - allows us to change the form of a property or method to meet the needs of a subclass

class bird():
    def __init__(self):
        print('bird is initialized')
        self.color = 'grey'

    def flight(self):
        print('Most of the birds can fly but some cannot')

class sparrow(bird):    # Inheriting bird
    def __init__(self):
        super().__init__()
        self.color = 'red brown'
        print('sparrow is inialized')

    def flight(self):   # Polymorphing the flight method for sparrows
        print('Sparrows can fly')
        
class ostrich(bird):
    def __init__(self):
        super().__init__()
        self.color = 'white brown'
        print('ostrich is inialized')

    def flight(self):
        print('Ostriches cannot fly')

my_sparrow = sparrow()
print(my_sparrow.color)
my_sparrow.flight()

my_ostrich = ostrich()
print(my_ostrich.color)
my_ostrich.flight()