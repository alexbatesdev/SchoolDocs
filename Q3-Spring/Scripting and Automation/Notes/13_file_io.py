# File I/O

# 'r'   read            Stream positioned at the beginning of file
# 'w'   write           File created if it doens't exist. Stream positioned at beginning of file
# 'a'   append          File created if it doesn't exist. Stream positioned at end of file
# 'r+'  read and write  Stream positioned at the beginning of the file
# 'w+'  read and write  File created if it doesn't exist. Stream positioned at begining of file
# 'a+'  read adn write  File created if it doesn't exist. Stream positioned at end of file

file = open('test1.txt', 'w')
file.write("Hello\n")
file.write("this is a test\n")
file.close()

file = open('test1.txt', 'r')
content = file.read()
file.close()
print(content)

file = open('test1.txt', 'a')
file.write('add one more line to this file\n')
file.close()

# open files using "with"
#-----------------------------

with open('test1.txt', 'w') as file:
    file.write('Wow the With works')
