import codewars_test as test


def order_weight(strng: str):
    if strng == "":
        return ""

    nums = strng.split(" ")
    numsAndWeights = []

    for num in nums:
        numWeight = 0
        for char in num:
            numWeight += int(char)
        numsAndWeights.append((num, numWeight))

    # This is sorting on the number weights
    inOrder = False
    while not inOrder:
        # Iterate through list to perform a round of sorting
        # I believe this is bubble sort, it's the only one I had the concept memorized and I presuem it's bubble sort because that's the simplest sort
        for index in range(len(numsAndWeights) - 1):
            if numsAndWeights[index][1] > numsAndWeights[index + 1][1]:
                greaterNum = numsAndWeights[index]
                lesserNum = numsAndWeights[index + 1]

                numsAndWeights[index + 1] = greaterNum
                numsAndWeights[index] = lesserNum

                inOrder = False
        inOrderCount = 0
        # Check if sorted
        for index in range(len(numsAndWeights) - 1):
            if numsAndWeights[index][1] <= numsAndWeights[index + 1][1]:
                inOrderCount += 1
        if inOrderCount == len(numsAndWeights) - 1:
            inOrder = True

    # This is sorting weight ties by string ordering standards
    inOrder = False
    while not inOrder:
        inOrderHistory = []
        for index in range(len(numsAndWeights) - 1):
            if numsAndWeights[index][1] == numsAndWeights[index + 1][1]:
                tempList = [numsAndWeights[index][0], numsAndWeights[index + 1][0]]
                tempList.sort()
                if tempList != [numsAndWeights[index][0], numsAndWeights[index + 1][0]]:
                    lesserNum = numsAndWeights[index + 1]
                    greaterNum = numsAndWeights[index]

                    numsAndWeights[index + 1] = greaterNum
                    numsAndWeights[index] = lesserNum

                    inOrder = False
                    inOrderHistory.append(False)
        for index in range(len(numsAndWeights) - 1):
            if numsAndWeights[index][1] == numsAndWeights[index + 1][1]:
                num1 = numsAndWeights[index][0]
                num2 = numsAndWeights[index + 1][0]
                numList = [num1, num2]
                sortedNumList = numList.copy()
                sortedNumList.sort()
                if numList == sortedNumList:
                    inOrderHistory.append(True)
        if not False in inOrderHistory:
            inOrder = True

    returnString = ""
    for pair in numsAndWeights:
        returnString += pair[0] + " "
    returnString = returnString.strip()
    return returnString


@test.describe("Weight for weight")
def tests():
    @test.it("basic tests")
    def basics1():
        test.assert_equals(order_weight("103 123 4444 99 2000"), "2000 103 123 4444 99")
        test.assert_equals(
            order_weight("2000 10003 1234000 44444444 9999 11 11 22 123"),
            "11 11 2000 10003 22 123 1234000 44444444 9999",
        )
        test.assert_equals(order_weight(""), "")
