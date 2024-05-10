# Introduction to String Basics

# A string in Python is a sequence of characters. 
# String literals can be created using single, double or triple quotes
# --------------------------

a = 'This uses single quotes'
b = "This uses double quotes"
c = """
    This uses triple quotes
    so it can span multiple lines.
"""

print(a)
print(b)
print(c)

# Format a string
# --------------------------
# String Modulo Operator
# e.g. <format_string> % <values>
# On the left side of the % operator, <format_string> is a string containing 
# one or more conversion specifiers. 
# The <values> on the right side get inserted into <format_string> 
# in place of the conversion specifiers. 
# The resulting formatted string is the value of the expression

print('%d %s cost $%.2f' % (6, 'bananas', 1.74))


# String .format method (similar to modulo operator)
print('{0} {1} cost ${2}'.format(6, 'bananas', 1.74))
print('{quantity} {item} cost ${price}'.format(quantity=6, item='bananas', price=1.74))

# the Formatted String Literal using f-string
quantity = 6
item = 'bananas'
price = 1.74
print(f'{quantity} {item} cost ${price}')

# Concatenation and string conversion
print(str(quantity) + ' ' + item + ' cost $' + str(price))

# string literals next to each other are concatenated
print('These' 'words' 'are' 'automatically' 'concantenated') 


# Use single quotes inside of double quotes 
# or double quotes inside of single quotes
print("I can't stop using contractions")
print('I once said, "Quotes are great"')

# Get the length of a string
print(len(a))
print(len(b))
print(len(c))

# Remove white space in a string
d = "  Remove the spaces   "
d1 = d.strip()
d2 = d.lstrip()
d3 = d.rstrip()

print(f"[{d1}]")
print(f"[{d2}]")
print(f"[{d3}]")

# Repeating strings
# --------------------------
r = 'repeat '
print(r * 5)
print('-' * 50)

# Accessing string elements
# --------------------------
# A string is simply a list of characters that can be retrieved using an index
# the index of a string starts at zero
s = 'abcdefg'
print(s[0])
print(s[4])

# When the index is negative, we retrieve elements from the end of the string
print(s[-1])
print(s[-2])

# We can retrieve a range of characters
# Indicate the start:end:step when retrieving a range
print(s[0:4])   # first 4 chars
print(s[2:6])   # chars from position 2 through position 6
print(s[:])     # full string
print(s[::-1])  # reverse the string

# Finding substrings
# --------------------------
# find() and rfind() return the index of the first occurrence of the substring in the string
# or they return -1 if not found
fruits = 'apple banana watermelon banana'
print(fruits.find('banana'))            # search from the start of the string
print(fruits.rfind('banana'))           # search from the end of the string
print(fruits.find('banana', 10))        # start searching at position 10
print(fruits.find('banana', 10, 20))    # start searching at position 10 through position 20

# Replacing strings
# --------------------------
# replace() replaced substrings in a string with other substrings
old = 'My name is Fred'
new = old.replace('Fred', 'Johnny')
print(new)

t = 'I saw a fox. The fox was a good fox.'
t1 = t.replace('fox', 'dog')
print(t1)
t2 = t.replace('fox', 'dog', 2) # specifiy how many occurances to replace
print(t2)

# Splitting and joining strings
# --------------------------
# split() will return a list of string cut using a separator. By default it splits on spaces.
abc = 'a b c d e f g h i'
letters = abc.split()
print(letters)

nums = '1,2,3,4,5,6,7,8,9'
values = nums.split(',')
print(values)

# join() will create one string from a list of strings separated by a given character
j = ':'.join(letters)
print(j)


# String case
# --------------------------
# Change the case of a string
v = 'UPPER lower'
print(v.upper())
print(v.lower())
print(v.swapcase())
print(v.title())
