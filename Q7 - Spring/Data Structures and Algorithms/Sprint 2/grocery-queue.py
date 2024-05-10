def queue_time(customers, n):
    selfCheckout = []
    time = 0
    # do = True
    while (
        len(customers) > 0 or len(selfCheckout) > 0
    ):  # A do while loop ladies and gentlemen
        # do = False
        for i in range(len(customers)):
            if len(selfCheckout) < n and len(customers) > 0:
                selfCheckout.append(customers[0])
                customers.pop(0)
        index = 0
        for i in range(len(selfCheckout)):
            selfCheckout[index] -= 1
            if selfCheckout[index] == 0:
                selfCheckout.remove(0)
                index -= 1
            index += 1
        time += 1
    print(time)
    return time


# queue_time([], 1)
queue_time([5], 1)
queue_time([2], 5)
queue_time([1, 2, 3, 4, 5], 1)
queue_time([1, 2, 3, 4, 5], 100)
queue_time([2, 2, 3, 3, 4, 4], 2)
# test.assert_equals(, 0, "wrong answer for case with an empty queue")
# test.assert_equals(, 5, "wrong answer for a single person in the queue")
# test.assert_equals(, 2, "wrong answer for a single person in the queue")
# test.assert_equals(, 15, "wrong answer for a single till")
# test.assert_equals(, 5, "wrong answer for a case with a large number of tills")
# test.assert_equals(, 9, "wrong answer for a case with two tills")
