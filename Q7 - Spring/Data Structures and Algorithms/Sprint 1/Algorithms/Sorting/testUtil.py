import random
def generateRandomArray(size):
    array = []
    for i in range(size):
        array.append(random.randint(0, size))
    return array