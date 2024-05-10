import codewars_test as test


def is_valid_walk(walk):
    # determine if walk is valid
    horiPos = 0
    vertPos = 0
    totalSteps = 0
    for step in walk:
        totalSteps += 1
        match step:
            case "n":
                horiPos += 1
                continue
            case "s":
                horiPos -= 1
                continue
            case "e":
                vertPos += 1
                continue
            case "w":
                vertPos -= 1
                continue
    if totalSteps == 10 and horiPos == 0 and vertPos == 0:
        return True
    else:
        return False


@test.describe("Walk Validator - fixed tests")
def sample_tests():
    @test.it("should return true for a valid walk")
    def _():
        test.assert_equals(
            is_valid_walk(["n", "s", "n", "s", "n", "s", "n", "s", "n", "s"]),
            True,
            "should return True",
        )

    @test.it("should return false if walk is too long")
    def _():
        test.assert_equals(
            is_valid_walk(["w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e"]),
            False,
            "should return False",
        )

    @test.it("should return false if walk is too short")
    def _():
        test.assert_equals(is_valid_walk(["w"]), False, "should return False")

    @test.it("should return false if walk does not bring you back to start")
    def _():
        test.assert_equals(
            is_valid_walk(["n", "n", "n", "s", "n", "s", "n", "s", "n", "s"]),
            False,
            "should return False",
        )
