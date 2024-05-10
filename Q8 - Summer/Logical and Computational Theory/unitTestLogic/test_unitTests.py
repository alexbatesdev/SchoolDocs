from unitTestLogic.thingsBeingTested import Zombie, GenLogic

# Pytest code coverage command:
# pytest --cov -v --cov-report  xml:cov.xml
# --cov triggers the pytest-cov plugin
# -v is verbose mode for pytest
# --cov-report xml:cov.xml tells pytest-cov to generate an XML report
# The xml report is for the coverage gutter plugin in VSCode

HEART_BEAT_TRUE = True
HEART_BEAT_FALSE = False

CAN_THINK_TRUE = True
CAN_THINK_FALSE = False


def test_isAlive_1():
    Zombo = Zombie()
    assert Zombo.isAlive(HEART_BEAT_TRUE, CAN_THINK_TRUE) == True


def test_isAlive_2():
    Zombo = Zombie()
    assert Zombo.isAlive(HEART_BEAT_TRUE, CAN_THINK_FALSE) == False


def test_isAlive_3():
    Zombo = Zombie()
    assert Zombo.isAlive(HEART_BEAT_FALSE, CAN_THINK_TRUE) == False


def test_isAlive_4():
    Zombo = Zombie()
    assert Zombo.isAlive(HEART_BEAT_FALSE, CAN_THINK_FALSE) == False


def test_MyImplies_1():
    GL = GenLogic()
    assert GL.MyImplies(True, True) == True


def test_MyImplies_2():
    GL = GenLogic()
    assert GL.MyImplies(True, False) == False


def test_MyImplies_3():
    GL = GenLogic()
    assert GL.MyImplies(False, True) == True


def test_MyImplies_4():
    GL = GenLogic()
    assert GL.MyImplies(False, False) == True


def test_MyOr_1():
    GL = GenLogic()
    assert GL.MyOr(True, True) == True


def test_MyOr_2():
    GL = GenLogic()
    assert GL.MyOr(True, False) == True


def test_MyOr_3():
    GL = GenLogic()
    assert GL.MyOr(False, True) == True


def test_MyOr_4():
    GL = GenLogic()
    assert GL.MyOr(False, False) == False
