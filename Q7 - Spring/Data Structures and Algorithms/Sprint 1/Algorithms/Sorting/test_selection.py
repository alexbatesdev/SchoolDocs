from sortingAlgorithms import selectionSort
from testUtil import generateRandomArray

def test_selectionSortRandomArray():
    array1 = generateRandomArray(10)
    assert selectionSort(array1) == sorted(array1)
    array2 = generateRandomArray(100)
    assert selectionSort(array2) == sorted(array2)

def test_selectionSortSortedArray():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert selectionSort(array1) == sorted(array1)

def test_selectionSortReverseSortedArray():
    array1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert selectionSort(array1) == sorted(array1)

def test_selectionSortEmptyArray():
    array1 = []
    assert selectionSort(array1) == sorted(array1)

def test_selectionSortSingleElementArray():
    array1 = [1]
    assert selectionSort(array1) == sorted(array1)

def test_selectionSortTwoElementArray():
    array1 = [2, 1]
    assert selectionSort(array1) == sorted(array1)