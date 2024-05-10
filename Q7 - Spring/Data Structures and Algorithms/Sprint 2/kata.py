def count_specMult(n, max_val):
    primesList = [
        2,
        3,
        5,
        7,
        11,
        13,
        17,
        19,
        23,
        29,
        31,
        37,
        41,
        43,
        47,
        53,
        59,
        61,
        67,
        71,
    ]
    incrementingNum = 1
    passTest = True
    output = []
    while incrementingNum < max_val:
        passTest = True
        for index in range(n):
            if incrementingNum % primesList[index] == 0:
                pass
            else:
                passTest = False
                break
        if passTest:
            output.append(incrementingNum)
        incrementingNum += 1
    print(output)
    return len(output)


def count_specMultMath(n, max_val):
    primesList = [
        2,
        3,
        5,
        7,
        11,
        13,
        17,
        19,
        23,
        29,
        31,
        37,
        41,
        43,
        47,
        53,
        59,
        61,
        67,
        71,
    ]
    num = 1
    for i in range(n):
        num *= primesList[i]
    return max_val // num


# print(count_specMult(3, 200))
# print(count_specMult(3, 1000))
# print(count_specMult(4, 500))
# print(count_specMult(4, 1000))
# print(count_specMult(4, 1000))
print(count_specMult(4, 1000000))
print(count_specMultMath(4, 1000000))
# print(count_specMult(4, 10000000))
# print(count_specMult(4, 100000000))
# print(count_specMult(4, 1000000000))

print(100000000 / 476190)
print(10000000 / 47619)
