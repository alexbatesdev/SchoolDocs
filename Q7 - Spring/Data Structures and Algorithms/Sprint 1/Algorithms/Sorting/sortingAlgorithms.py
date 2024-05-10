from timeit import default_timer as timer
from colorama import Fore, Back, Style
import math


# Bubble sort
def bubbleSort(array: list):
    array = array.copy()
    unsorted = True
    bigLoops = 0
    while unsorted:
        swapOperation = False
        for index in range(len(array) - 1 - bigLoops):
            if array[index + 1] < array[index]:
                # printArray(array)
                # input(">")
                swapOperation = True
                greaterNum = array[index]
                lesserNum = array[index + 1]
                array[index] = lesserNum
                array[index + 1] = greaterNum
        if swapOperation == False:
            # printArray(array)
            unsorted = False
        bigLoops += 1
    return array


# Selection sort
def selectionSort(array: list):
    def findIndexOfSmallestNum(array: list):
        smallest = array[0]
        for number in array:
            if number < smallest:
                smallest = number

        return array.index(smallest)

    array = array.copy()
    if len(array) == 0:
        return []
    unsorted = True
    iterations = 0
    while unsorted:
        # printArray(array)
        # input(">")
        smallestNumIndex = findIndexOfSmallestNum(array[iterations:])
        smallestNum = array[smallestNumIndex + iterations]
        swappedNum = array[iterations]
        array[iterations] = smallestNum
        array[smallestNumIndex + iterations] = swappedNum
        iterations += 1
        if iterations == len(array):
            # printArray(array)
            unsorted = False
    return array


# Insertion sort
def insertionSort(array: list):
    # Select item (starting at index 1, not 0)
    # Compare with item before selected number
    # If item before selected number is greater than selected number
    # move item left, check again for lesser number to the left
    # if Item before selected number is less than current number then place current number
    array = array.copy()
    for index in range(len(array)):
        if index == 0:
            continue
        grabbedNum = array[index]
        unnamedBool = True
        reach = 1
        reachOffset = 0
        while unnamedBool:
            if index - reach < 0:
                unnamedBool = False
                break
            if array[index - reach] > grabbedNum:
                array[index - reachOffset] = array[index - reach]
                array[index - reach] = grabbedNum
                reachOffset += 1
                reach += 1
                # printArray(array)
                # input(">")
            else:
                unnamedBool = False
    return array


def insertionSortTogether(array: list):
    for index in range(len(array)):
        compare = array[index]
        for iterator in range(index, 0, -1):
            if array[iterator - 1] > compare:
                array[iterator - 1] = array[iterator]
            else:
                break
            array[iterator - 1] = compare
    return array


# Merge Sort
def mergeSort(array: list):
    def pseudoCode():
        """
        split array into partitions of 1
        Merge partitions 1 and 2, sort
        Merge partitions 3 and 4, sort
        repeat until all partitions merged once
        merge partions [1,2] and [3,4], sort
        repeat merging and sorting larger and larger partitions until complete
        """

    if len(array) <= 1:
        return array
    array = array.copy()
    otherArray = []
    for item in array:
        otherArray.append([item])
    print(otherArray)

    while len(otherArray) > 1:
        # Merge Partitions
        for item in otherArray[::2]:
            if otherArray.index(item) + 1 < len(otherArray):
                tempPartition = __msort__(item, otherArray[otherArray.index(item) + 1])
                otherArray.pop(otherArray.index(item) + 1)
                otherArray[otherArray.index(item)] = tempPartition
        print(otherArray)

    return otherArray[0]


def __msort__(arr1, arr2):
    output = []
    # Note here, I did need a hint to figure this out
    # I asked chatGPT to give me a hint without showing me any code or giving me the solution
    # The hint I needed was to use two indexes, one for each array
    # I was trying to use a single index for both arrays and couldn't figure out how to compare the arrays properly like this
    arr1Index = 0
    arr2Index = 0
    while arr1Index < len(arr1) and arr2Index < len(arr2):
        if arr1[arr1Index] < arr2[arr2Index]:
            output.append(arr1[arr1Index])
            arr1Index += 1
        else:
            output.append(arr2[arr2Index])
            arr2Index += 1
    if arr1Index < len(arr1):
        output.extend(arr1[arr1Index:])
    if arr2Index < len(arr2):
        output.extend(arr2[arr2Index:])
    print(output)
    return output


def mergeSortRecursive(array: list):
    def pseudoCode():
        """
        Merge Sort:
        Takes in array

        split then merge

        Split arrays that are longer than 1

        When arrays are length of one start merging process

        Work your way up the merge, comparing the first index of each array


        def margesort(homie: List):
            if len(homie) <= 1:
                return homie
            arrayL = split(homie, Left)
            arrayR = split(homie, right)
            margesort(arrayL)
            margesort(arrayR)
            return msort(arrayL, arrrayR)
        """

    print(array)
    if len(array) <= 1:
        return array
    midpoint = math.floor(len(array) * 0.5)
    # split array into left and right
    arrayLeft = array[:midpoint]
    arrayRight = array[midpoint:]
    arrayLeft = mergeSortRecursive(arrayLeft)
    arrayRight = mergeSortRecursive(arrayRight)
    output = __msort__(arrayLeft, arrayRight)
    return output


def quickSort(array):
    def pseudoCode():
        """
        Find pivot
        Currently pivot is the first item of the array
        while largerIndex != lesserIndex and not lesserIndex < largerIndex:
        Iterate lesser and larger checkers
        Solve pivot
        Partition
        Solve pivot
        partition
        """

    def __quickSort__(array: list, start, end):
        # printArray(array)
        if len(array) <= 1:
            return array
        if start >= end:
            return array

        pivotIndex = start
        pivot = array[pivotIndex]

        array = partition(array, pivot)

        if pivot != array[pivotIndex]:
            pivotIndex = array.index(pivot)

        quicksort_called = False
        if start < pivotIndex - 1:
            quicksort_called = True
            array = __quickSort__(array, start, pivotIndex - 1)
        if pivotIndex + 1 < end:
            quicksort_called = True
            array = __quickSort__(array, pivotIndex + 1, end)

        # if not quicksort_called:
        # print(array)
        return array

    array = array.copy()
    start = 0
    end = len(array)
    array = __quickSort__(array, start, end)
    # printArray(array)
    return array


def partition(array, pivot):
    lesserArray = []
    greaterArray = []
    pivotArray = []

    for index in range(len(array)):
        if pivot < array[index]:
            greaterArray.append(array[index])
        elif pivot > array[index]:
            lesserArray.append(array[index])
        else:
            pivotArray.append(array[index])
    return lesserArray + pivotArray + greaterArray


# Bogo sorter (OLD) I found this in a repl I made 2 years ago, I don't remember how it works
import random


def bogoSort(array):
    listy = array.copy()
    # printArray(listy)
    attempts = 0
    while listy != sorted(listy):
        attempts += 1
        # printArray(listy)
        n = len(listy)
        for i in range(0, n):
            r = random.randint(0, n - 1)
            listy[i], listy[r] = listy[r], listy[i]
    # printArray(listy)
    print("Solved in " + str(attempts) + " attempts")


# ------------------------------------------------------------


def timetheFunc(func, array):
    print("Timing function: " + func.__name__)
    start = timer()
    func(array)
    end = timer()
    print("Function took " + str((end - start) * 10000) + " ms to execute")


def randArray(length):
    listy = []
    for i in range(length):
        listy.append(random.randint(0, 100))
    return listy


originalArray = []


def printArray(array):
    global originalArray
    if originalArray == []:
        originalArray = array.copy()
        print("Original Array Set: " + str(originalArray))

    ForeArray = [
        Fore.BLACK,
        Fore.RED,
        Fore.GREEN,
        Fore.YELLOW,
        Fore.BLUE,
        Fore.MAGENTA,
        Fore.CYAN,
        Fore.WHITE,
    ]
    BackArray = [
        Back.RED,
        Back.GREEN,
        Back.YELLOW,
        Back.BLUE,
        Back.MAGENTA,
        Back.CYAN,
        Back.WHITE,
    ]
    StyleArray = [Style.NORMAL, Style.BRIGHT]
    for i in range(len(array)):
        print(
            ForeArray[originalArray.index(array[i]) % len(ForeArray)]
            + BackArray[originalArray.index(array[i]) % len(BackArray)]
            + StyleArray[originalArray.index(array[i]) % len(StyleArray)]
            + str(array[i])
            + Style.RESET_ALL,
            end=" ",
        )
    print()


def increasingArray(
    function, auto=False, delay_seconds=0.5, initialArrayLength=3, loopLimit=-1
):
    import time

    global originalArray
    running = True
    loops = 0
    while running:
        if loopLimit != -1:
            loops += 1
            if loops >= loopLimit:
                running = False

        array = randArray(initialArrayLength)
        originalArray = array.copy()
        print("Initial Array:", array)
        print("Final Array:", function(array))
        print("Known good Sort: ", sorted(array))
        if auto:
            time.sleep(delay_seconds)
        else:
            inpuut = input("Press enter to continue: ")
            if inpuut == "q":
                running = False
        originalArray = []
        initialArrayLength += 1


array = [4, 5, 50, 2, 10, 17, 20]
array2 = [2, 6, 4, 3, 1]
array3 = [11, 5, 50, 20, 9, 11]
array4 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
array5 = [-1, -2, -3, -4, -5, -6, -7, -8, -9, -10]

# timetheFunc(bubbleSort, array)
# timetheFunc(selectionSort, array)
# timetheFunc(insertionSort, array)
# timetheFunc(bogoSort, array)
# timetheFunc(mergeSort, array)
# timetheFunc(quickSort, array)

# bubbleSort(array5)
# selectionSort(array5)
# insertionSort(array5)
# bogoSort(array5)
# mergeSort(array5)


# increasingArray(
#     function=bubbleSort,
#     auto=True,
#     delay_seconds=0.5,
#     initialArrayLength=5,
#     loopLimit=10,
# )
