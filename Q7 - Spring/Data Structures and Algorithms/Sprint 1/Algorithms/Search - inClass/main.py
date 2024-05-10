import math


def linearSearch(arr, target):
    for number in arr:
        if number == target:
            return arr.index(number)
    return -1


def binarySearch(arr, target):
    workingArray = arr.copy()
    isFound = False
    targetIndex = 0

    while not isFound:
        midIndex = math.floor(0.5 * len(workingArray))
        midpoint = workingArray[midIndex]
        if midpoint > target:
            workingArray = workingArray[:midIndex]
        elif midpoint <= target:
            workingArray = workingArray[midIndex:]
            targetIndex += midIndex
        if midpoint == target:
            isFound = True
            return targetIndex
        elif len(workingArray) == 1:
            return -1


items = [2, 4, 8, 9, 11, 16, 20, 21, 30, 31, 32, 33, 34, 40, 41, 42, 45, 46, 50]
target = 30

foundIndex = binarySearch(items, target)
if foundIndex == -1:
    print(f"{target} not found")
else:
    print(f"{target} found at index {foundIndex}")
    print(f"Item value at that index is: {items[foundIndex]}")
