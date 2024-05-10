from unitTestLogic.thingsBeingTested import DeMogorgansLaw


def test_notAandB_with_notAornotB_1():
    DML = DeMogorgansLaw()
    assert DML.Not_AandB(True, True) == DML.NotA_or_NotB(True, True)


def test_notAandB_with_notAornotB_2():
    DML = DeMogorgansLaw()
    assert DML.Not_AandB(True, False) == DML.NotA_or_NotB(True, False)


def test_notAandB_with_notAornotB_3():
    DML = DeMogorgansLaw()
    assert DML.Not_AandB(False, True) == DML.NotA_or_NotB(False, True)


def test_notAandB_with_notAornotB_4():
    DML = DeMogorgansLaw()
    assert DML.Not_AandB(False, False) == DML.NotA_or_NotB(False, False)


def test_notAorB_with_notAandnotB_1():
    DML = DeMogorgansLaw()
    assert DML.Not_AorB(True, True) == DML.NotA_and_NotB(True, True)


def test_notAorB_with_notAandnotB_2():
    DML = DeMogorgansLaw()
    assert DML.Not_AorB(True, False) == DML.NotA_and_NotB(True, False)


def test_notAorB_with_notAandnotB_3():
    DML = DeMogorgansLaw()
    assert DML.Not_AorB(False, True) == DML.NotA_and_NotB(False, True)


def test_notAorB_with_notAandnotB_4():
    DML = DeMogorgansLaw()
    assert DML.Not_AorB(False, False) == DML.NotA_and_NotB(False, False)
