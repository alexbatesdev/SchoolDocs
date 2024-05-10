numeralDict = {
    "M": 1000,
    "CM": 900,
    "D": 500,
    "CD": 400,
    "C": 100,
    "XC": 90,
    "L": 50,
    "XL": 40,
    "X": 10,
    "IX": 9,
    "V": 5,
    "IV": 4,
    "I": 1,
}
dictKeys = []
for key in numeralDict.keys():
    dictKeys.append(key)


def numeral(n):
    """Return a string containing the Roman numeral representation of a n."""
    # repeatedly subtract the largest numeral you can
    # add to string
    i = 0
    output = ""
    while n > 0:
        if n - numeralDict[dictKeys[i]] >= 0:
            n -= numeralDict[dictKeys[i]]
            output = output + dictKeys[i]
        # elif n - numeralDict[dictKeys[i]] == -1:
        #     n -= numeralDict[dictKeys[i]] + 1
        #     output = output + "I" + dictKeys[i]
        else:
            i += 1
        # check = checkDupes(output)
        # print("wip", output, n)
        # # print(check)
        # if check[0]:
        #     output = output.replace(
        #         output[check[1] : (check[1] + check[2])],
        #         dictKeys[i] + dictKeys[i - 1],
        #     )
        # print("n:", n)
        # print("output:", output)
        # print("i:", i, "key:", dictKeys[i], "value:", numeralDict[dictKeys[i]])
        # input("Press key to continue")

    # print("FINAL OUTPUT:", output)
    return output


def checkDupes(string):
    count = 0
    index = 0
    for i in range(len(string) - 1):
        if string[i] == string[i + 1]:
            if count == 0:
                index = i
            count += 1
        else:
            count = 0

    if count >= 3:
        return [True, index, count + 1]
    else:
        return [False, 0, 0]


print(numeral(1), 1)
print(numeral(4), 4)
print(numeral(6), 6)
print(numeral(14), 14)
print(numeral(21), 21)
print(numeral(89), 89)
print(numeral(91), 91)
print(numeral(400), 400)
print(numeral(984), 984)
print(numeral(1000), 1000)
print(numeral(1889), 1889)
print(numeral(1989), 1989)
