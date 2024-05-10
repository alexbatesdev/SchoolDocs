package edu.neumont;

public class Walker extends Zombie{
    static final int MIN_HP = 15;
    static final int MAX_HP = 30;
    static final int MIN_SPEED = 6;
    static final int MAX_SPEED = 10;
    //baseHP 9-19
    //speed 6-10

    public Walker() {
        setArms(RNG.getInt(MIN_ARMS, MAX_ARMS+1));
        setLegs(RNG.getInt(MIN_LEGS, MAX_LEGS+1));
        setBaseHP(RNG.getInt(MIN_HP, MAX_HP+1));
        setSpeed(RNG.getInt(MIN_SPEED, MAX_SPEED+1));
    }



    @Override
    int attack(int a_roll) {
        if (a_roll == 20) {
            return roll(3, 6) * 2;
        } else if (a_roll >= 8 && a_roll <= 19) {
            return roll(3, 6);
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
        return super.toString() + "\n This zombie is a walker class";
    }
}
