# Read from file
# iterate through word
# Generate isomorph map for word
# Sort into buckets
# Exact match
# Check words isomorph map with eachother, if matching they are exact matches
# Loose match
# Sort isomrph map from smallest to largest
# Check words isomorph map with eachother, if matching they are loose matches
# No match
# No match


def isomorph_map(word):
    uniqueCharacters = []
    uniqueCharPattern = []
    uniqueCharCount = []

    index = 0
    uniqueCharNum = -1
    for letter in word:
        if letter not in uniqueCharacters:
            uniqueCharacters.append(letter)
            uniqueCharNum += 1
            uniqueCharPattern.append(uniqueCharNum)
            uniqueCharCount.append(1)
        else:
            uniqueCharCount[uniqueCharacters.index(letter)] += 1
            uniqueCharPattern.append(uniqueCharacters.index(letter))
        index += 1

    return [uniqueCharacters, uniqueCharPattern, uniqueCharCount]


def keyString(key):
    keyString = ""
    for index in key:
        keyString += str(index) + " "
    return keyString.strip()


def getExactIsomorphs(words):
    exactIsomorphCandidates = {}

    for word in words:
        word_map = isomorph_map(word)
        word_map[1].sort()

        if keyString(word_map[1]) not in exactIsomorphCandidates:
            exactIsomorphCandidates[keyString(word_map[1])] = [word]
        else:
            exactIsomorphCandidates[keyString(word_map[1])].append(word)

    exactIsomorphs = {}

    for key in exactIsomorphCandidates:
        if len(exactIsomorphCandidates[key]) > 1:
            exactIsomorphs[key] = exactIsomorphCandidates[key]
        else:
            if exactIsomorphCandidates[key][0] not in noMatchWords:
                noMatchWords.append(exactIsomorphCandidates[key][0])

    return exactIsomorphs


def getLooseIsomorphs(words):
    looseIsomorphCandidates = {}

    for word in words:
        word_map = isomorph_map(word)
        word_map[2].sort()
        if keyString(word_map[2]) not in looseIsomorphCandidates:
            looseIsomorphCandidates[keyString(word_map[2])] = [word]
        else:
            looseIsomorphCandidates[keyString(word_map[2])].append(word)

    looseIsomorphs = {}

    for key in looseIsomorphCandidates:
        if len(looseIsomorphCandidates[key]) > 1:
            looseIsomorphs[key] = looseIsomorphCandidates[key]
        else:
            if looseIsomorphCandidates[key][0] not in noMatchWords:
                noMatchWords.append(looseIsomorphCandidates[key][0])

    return looseIsomorphs


print("Enter file path (./input1.txt): ")
file_path = input()
if file_path == "":
    print("No file path entered, using default")
    file_path = "./input1.txt"

with open(file_path, "r") as file:
    with open("./output.txt", "w") as outputFile:
        words = file.read().splitlines()

        outputFile.write("Words:\n")
        print("Words: ")
        for word in words:
            outputFile.write(word + " ")
            print(word)
        outputFile.write("\n")
        print()

        noMatchWords = []

        exactIsomorphs = getExactIsomorphs(words)
        looseIsomorphs = getLooseIsomorphs(words)

        for word in noMatchWords:
            for key in exactIsomorphs:
                if word in exactIsomorphs[key]:
                    exactIsomorphs[key].remove(word)
            for key in looseIsomorphs:
                if word in looseIsomorphs[key]:
                    looseIsomorphs[key].remove(word)

        outputFile.write("\nExact Isomorphs:\n")
        print("Exact Isomorphs:")
        for key in exactIsomorphs:
            exactIsomorphs[key].sort()
            outputFile.write(key + ": ")
            print(key, end=": ")
            for index in exactIsomorphs[key]:
                outputFile.write(index + " ")
                print(index, end=" ")
            outputFile.write("\n")
            print()

        outputFile.write("\nLoose Isomorphs:\n")
        print("\nLoose Isomorphs:")
        for key in looseIsomorphs:
            looseIsomorphs[key].sort()
            outputFile.write(key + ": ")
            print(key, end=": ")
            for index in looseIsomorphs[key]:
                outputFile.write(index + " ")
                print(index, end=" ")
            outputFile.write("\n")
            print()
        outputFile.write("\nNon-Isomorphs:\n")
        print("\nNon-Isomorphs:")
        noMatchWords.sort()
        for word in noMatchWords:
            outputFile.write(word + " ")
            print(word, end=" ")
