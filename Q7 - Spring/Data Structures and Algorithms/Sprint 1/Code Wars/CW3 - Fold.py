import codewars_test as test
import math


def fold_array(array: list, runs):
    returnArray = array.copy()

    for iterations in range(runs):
        # print(f"Array:\n{returnArray}\nIteration: {iterations}")
        foldIndex = 0
        if len(returnArray) % 2 == 0:
            foldIndex = (0.5 * len(returnArray)) + 0.5
        else:
            foldIndex = (0.5 * len(returnArray)) - 0.5
        # print(f"Fold-Index: {foldIndex}")

        newArray = []
        revArray = returnArray.copy()
        revArray.reverse()

        for index in range(math.floor(foldIndex)):
            newArray.append(returnArray[index] + revArray[index])
        if foldIndex == math.floor(foldIndex):
            # print("WHOLE NUMBER FOLD INDEX!")
            newArray.append(returnArray[int(foldIndex)])

        returnArray = newArray.copy()
    # print(f"Final Array: {returnArray}\n\n")
    return returnArray


arr = [1, 2, 3, 4, 5]

tests = [
    [[arr, 1], [6, 6, 3]],
    [[arr, 2], [9, 6]],
    [[arr, 3], [15]],
    [[[-9, 9, -8, 8, 66, 23], 1], [14, 75, 0]],
]

for inp, exp in tests:
    # fold_array(*inp)
    test.assert_equals(fold_array(*inp), exp)
