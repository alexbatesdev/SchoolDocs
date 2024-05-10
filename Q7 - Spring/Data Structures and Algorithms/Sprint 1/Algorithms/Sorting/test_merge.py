from sortingAlgorithms import mergeSortRecursive as mergeSort
from testUtil import generateRandomArray


def test_mergeSortRandomArray():
    array1 = generateRandomArray(10)
    assert mergeSort(array1) == sorted(array1)
    array2 = generateRandomArray(100)
    assert mergeSort(array2) == sorted(array2)


def test_mergeSortSortedArray():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert mergeSort(array1) == sorted(array1)


def test_mergeSortReverseSortedArray():
    array1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert mergeSort(array1) == sorted(array1)


def test_mergeSortEmptyArray():
    array1 = []
    assert mergeSort(array1) == sorted(array1)


def test_mergeSortSingleElementArray():
    array1 = [1]
    assert mergeSort(array1) == sorted(array1)
