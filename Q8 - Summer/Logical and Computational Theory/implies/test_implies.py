from implies.implies import implies


def test_implies():
    assert implies(True, True) == True
    assert implies(True, False) == False
    assert implies(False, True) == True
    assert implies(False, False) == True
