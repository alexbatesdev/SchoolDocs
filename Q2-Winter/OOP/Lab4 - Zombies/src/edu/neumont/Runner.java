package edu.neumont;

public class Runner extends Zombie{
    static final int MIN_HP = 10;
    static final int MAX_HP = 22;
    static final int MIN_SPEED = 15;
    static final int MAX_SPEED = 25;
    //baseHP 10-22
    //speed 15-25
    //climb speed 1/3 of speed

    public Runner() {
        setArms(RNG.getInt(MIN_ARMS, MAX_ARMS+1));
        setLegs(RNG.getInt(MIN_LEGS, MAX_LEGS+1));
        setBaseHP(RNG.getInt(MIN_HP, MAX_HP+1));
        setSpeed(RNG.getInt(MIN_SPEED, MAX_SPEED+1));
    }

    public int getClimbSpeed() {
        return (int)(speed * (1.0/3.0));
    }

    @Override
    int attack(int a_roll) {
        if (a_roll >= 19) {
            return roll(2, 8) * 2;
        } else if (a_roll >= 5 && a_roll <= 18) {
            return roll(2, 8);
        }
        return 0;
    }

    @Override
    public String attackString(int a_roll) {
        if (a_roll == 20) {
            return "Critical Hit!";
        } else if (a_roll >= 8 && a_roll <= 19) {
            return "Hit!";
        }
        return "Miss!";
    }

    @Override
    public void setBaseHP(int baseHP) {
        if (baseHP < MIN_HP || baseHP > MAX_HP) {
            throw new IllegalArgumentException("Invalid baseHP");
        }
        this.baseHP = baseHP;
    }

    @Override
    public void setSpeed(int speed) {
        if (speed < MIN_SPEED || speed > MAX_SPEED) {
            throw new IllegalArgumentException("Invalid speed");
        }
        this.speed = speed;
    }

    @Override
    public String toString() {
        return super.toString() + "\n This zombie is a runner class, this zombie is much faster and can climb at the speed of " + getClimbSpeed();
    }
}
