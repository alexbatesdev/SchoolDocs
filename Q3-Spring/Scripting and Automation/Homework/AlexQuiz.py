import random as r

questions = [
    (1775, "was the start of the Revolutionary War?"),
    (1783, "was the United States Constitution signed?"),
    (1865, "was President Lincoln assassinated?"),
    (1901, "was Theodore Roosevelt's first day in office as President of the United States?"),
    (1939, "was the beginning of World War II?"),
    (1975, "was the first personal computer introduced?"),
    (1989, "was the Berlin Wall taken down?"),
    (1492, "did Columbus sail the ocean blue?"),
    (2000, "was Y2K?"),
    (2012, "was the End of The World!?!? (According to the Mayans)"),
]
loop = 0
score = 0
correct = 0
within5 = 0
within10 = 0
within20 = 0
incorrect = 0

r.shuffle(questions)

for i in range(len(questions)):
    print(f'Score: {score}')
    print('-' * 50)
    print(f'Question {i + 1} of {len(questions)}')
    prompt = f'What year {questions[i][1]}'
    print(prompt)
    try:
        userAnswer = int(input(">>"))
    except:
        print("That's not a number.")
        print("As punishment for your foolishness you will have to start over.")
        break
    if userAnswer == questions[i][0]:
        score += 10
        print("Correct! Plus 10 points!")
        correct += 1 
    elif (abs(userAnswer - questions[i][0]) < 5):
        score += 5
        within5 += 1
        print(f"Incorrect! But your guess was within five years of the correct answer of {questions[i][0]}, so I'll give you 5 points")
    elif (abs(userAnswer - questions[i][0]) < 10):
        score += 2
        within10 += 1
        print(f"Incorrect! But your guess was within ten years of the correct answer of {questions[i][0]}, so I'll give you 2 points")
    elif (abs(userAnswer - questions[i][0]) < 20):
        score += 1
        within20 += 1
        print(f"Incorrect! But your guess was within twenty years of the correct answer of {questions[i][0]}, so I'll give you a point")
    else:
        print(f"Incorrect! The correct answer was {questions[i][0]}")
        incorrect += 1


print('-' * 50)
print("Quiz Complete")
print(f'Score: {score}')
print(f'Correct: {correct}')
print(f'Within 5 Years: {within5}')
print(f'Within 10 Years: {within10}')
print(f'Within 20 Years: {within20}')
print(f'Incorrect: {incorrect}')