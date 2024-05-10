from sortingAlgorithms import insertionSortTogether as insertionSort
from testUtil import generateRandomArray


def test_insertionSortRandomArray():
    array1 = generateRandomArray(10)
    assert insertionSort(array1) == sorted(array1)
    array2 = generateRandomArray(100)
    assert insertionSort(array2) == sorted(array2)


def test_insertionSortSortedArray():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert insertionSort(array1) == sorted(array1)


def test_insertionSortReverseSortedArray():
    array1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert insertionSort(array1) == sorted(array1)


def test_insertionSortEmptyArray():
    array1 = []
    assert insertionSort(array1) == sorted(array1)


def test_insertionSortSingleElementArray():
    array1 = [1]
    assert insertionSort(array1) == sorted(array1)


def test_insertionSortTwoElementArray():
    array1 = [2, 1]
    assert insertionSort(array1) == sorted(array1)
