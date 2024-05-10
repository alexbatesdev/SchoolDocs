class Zombie:
    def isAlive(self, hasHeartBeat: bool, canThink: bool):
        return hasHeartBeat and canThink


class GenLogic:
    def MyOr(self, a: bool, b: bool):
        return a or b

    def MyImplies(self, a: bool, b: bool):
        # return (not a) or b
        if a:
            return b
        else:
            return True


class DeMogorgansLaw:
    def Not(self, a: bool):
        return not a

    def And(self, a: bool, b: bool):
        return a and b

    def Or(self, a: bool, b: bool):
        return a or b

    # pair 1
    def Not_AandB(self, a: bool, b: bool):
        return self.Not(self.And(a, b))

    def NotA_or_NotB(self, a: bool, b: bool):
        return self.Or(self.Not(a), self.Not(b))

    # pair 2
    def Not_AorB(self, a: bool, b: bool):
        return self.Not(self.Or(a, b))

    def NotA_and_NotB(self, a: bool, b: bool):
        return self.And(self.Not(a), self.Not(b))


class PracticalUnitTests:
    # In retrospect I do not know why I made functions for these instead of just using the operators
    def Not(self, a: bool):
        return not a

    def And(self, a: bool, b: bool):
        return a and b

    def Or(self, a: bool, b: bool):
        return a or b

    def AandB_or_BorC(self, a: bool, b: bool, c: bool):
        return self.Or(self.And(a, b), self.Or(b, c))

    def AandNOTC_or_NOT_AandC(self, a: bool, b: bool, c: bool):
        return self.Or(self.And(a, self.Not(c)), self.Not(self.And(b, c)))
