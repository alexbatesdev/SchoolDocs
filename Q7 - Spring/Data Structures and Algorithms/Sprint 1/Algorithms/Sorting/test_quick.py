from sortingAlgorithms import quickSort, partition
from testUtil import generateRandomArray


def test_quickSortRandomArray():
    array1 = generateRandomArray(10)
    assert quickSort(array1) == sorted(array1)
    array2 = generateRandomArray(100)
    assert quickSort(array2) == sorted(array2)


def test_quickSortArrayWithDuplicates():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert quickSort(array1) == sorted(array1)


def test_quickSortArrayWithRepeatingNumber():
    array1 = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    assert quickSort(array1) == sorted(array1)


def test_quickSortArrayWithNegativeNumbers():
    array1 = [-1, -2, -3, -4, -5, -6, -7, -8, -9, -10]
    assert quickSort(array1) == sorted(array1)


def test_quickSortArrayWithNegativeAndPositiveNumbers():
    array1 = [-1, 2, -3, 4, -5, 6, -7, 8, -9, 10]
    assert quickSort(array1) == sorted(array1)


def test_quickSortSortedArray():
    array1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    assert quickSort(array1) == sorted(array1)


def test_quickSortReverseSortedArray():
    array1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    assert quickSort(array1) == sorted(array1)


def test_quickSortEmptyArray():
    array1 = []
    assert quickSort(array1) == sorted(array1)


def test_quickSortSingleElementArray():
    array1 = [1]
    assert quickSort(array1) == sorted(array1)


def test_quickSortTwoElementArray():
    array1 = [2, 1]
    assert quickSort(array1) == sorted(array1)


def test_quickSortThreeElementArray():
    array1 = [2, 1, 3]
    assert quickSort(array1) == sorted(array1)


def test_partitionUnsortedArray():
    array = [1, 2, 6, 2, 4, 17, 12, 20, 18]
    assert partition(array, 4) == [1, 2, 2, 4, 6, 17, 12, 20, 18]


def test_partitionWithPivotNotInArray():
    array = [1, 2, 6, 2, 4, 17, 12, 20, 18]
    assert partition(array, 10) == [1, 2, 6, 2, 4, 17, 12, 20, 18]


def test_partitionWithPivotAsFirstElement():
    array = [1, 2, 6, 2, 4, 17, 12, 20, 18]
    assert partition(array, 1) == [1, 2, 6, 2, 4, 17, 12, 20, 18]


def test_partitionWithPivotAsLastElement():
    array = [1, 2, 6, 2, 4, 17, 12, 20, 18]
    assert partition(array, 18) == [1, 2, 6, 2, 4, 17, 12, 18, 20]


def test_partitionWithPivotAsMiddleElement():
    array = [1, 2, 6, 2, 4, 17, 12, 20, 18]
    assert partition(array, 6) == [1, 2, 2, 4, 6, 17, 12, 20, 18]


def test_partitionWithPivotAsOnlyElement():
    array = [1]
    assert partition(array, 1) == [1]
