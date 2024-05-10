# Concatenates characters in an array and adds numbers in an array
def HelloWorld(array, index=0):
    # Stop case
    if index == len(array) - 1:
        print(array[index], index, sep=": ")
        return array[index]
    else:
        print(array[index], index, sep=": ")
        return array[index] + HelloWorld(array, index + 1)


array = ["h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d", "!"]
array2 = [1, 9, 7, 13, 0, 10, 5, 5]

# print(HelloWorld(array2, 0))
