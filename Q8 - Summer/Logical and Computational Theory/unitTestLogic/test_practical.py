from unitTestLogic.thingsBeingTested import PracticalUnitTests


def test_AandB_or_BorC_1():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(True, True, True) == True


def test_AandB_or_BorC_2():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(True, True, False) == True


def test_AandB_or_BorC_3():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(True, False, True) == True


def test_AandB_or_BorC_4():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(True, False, False) == False


def test_AandB_or_BorC_5():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(False, True, True) == True


def test_AandB_or_BorC_6():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(False, True, False) == True


def test_AandB_or_BorC_7():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(False, False, True) == True


def test_AandB_or_BorC_8():
    practical = PracticalUnitTests()
    assert practical.AandB_or_BorC(False, False, False) == False


# ---------------------------------------------- DELINEATION ----------------------------------------------


def test_AandNOTC_or_NOT_AandC_1():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(True, True, True) == False


def test_AandNOTC_or_NOT_AandC_2():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(True, True, False) == True


def test_AandNOTC_or_NOT_AandC_3():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(True, False, True) == True


def test_AandNOTC_or_NOT_AandC_4():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(True, False, False) == True


def test_AandNOTC_or_NOT_AandC_5():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(False, True, True) == False


def test_AandNOTC_or_NOT_AandC_6():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(False, True, False) == True


def test_AandNOTC_or_NOT_AandC_7():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(False, False, True) == True


def test_AandNOTC_or_NOT_AandC_8():
    practical = PracticalUnitTests()
    assert practical.AandNOTC_or_NOT_AandC(False, False, False) == True
