from sortingAlgorithms import bubbleSort
from testUtil import generateRandomArray

def test_bubbleSortRandomArray():
    array1 = generateRandomArray(10)
    assert bubbleSort(array1) == sorted(array1)
    array2 = generateRandomArray(100)
    assert bubbleSort(array2) == sorted(array2)

def test_bubbleSortSortedArray():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert bubbleSort(array1) == sorted(array1)

def test_bubbleSortReverseSortedArray():
    array1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert bubbleSort(array1) == sorted(array1)

def test_bubbleSortEmptyArray():
    array1 = []
    assert bubbleSort(array1) == sorted(array1)

def test_bubbleSortSingleElementArray():
    array1 = [1]
    assert bubbleSort(array1) == sorted(array1)

def test_bubbleSortTwoElementArray():
    array1 = [2, 1]
    assert bubbleSort(array1) == sorted(array1)