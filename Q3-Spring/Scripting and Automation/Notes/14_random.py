# Random
# Python has a built-in module that you can use to make random numbers

import random

print(random.random())              # A random float between 0 and 1
print(random.uniform(20, 60))       # A Random float number between a range 
print(random.randint(0, 2))         # A random number between the given range (includes both numbers)
print(random.randrange(0,100))      # A random number between the given range (includes both numbers)


my_list = [1,2,3,4,5,6,7,8,9,0]

print(random.choice(my_list))       # randomly pick an item from a list

random.shuffle(my_list)             # shuffle the list 
print(my_list)

print(random.sample(my_list, k=3))  #Random k size sample of a list

