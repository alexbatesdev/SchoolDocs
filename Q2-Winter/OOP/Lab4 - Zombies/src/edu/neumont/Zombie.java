package edu.neumont;

import java.util.Random;

public abstract class Zombie {
    static final int MIN_ARMS = 0;
    static final int MAX_ARMS = 2;
    static final int MIN_LEGS = 0;
    static final int MAX_LEGS = 2;
    protected int arms;
    protected int legs;
    protected int baseHP;
    protected int speed;
    static Random random = new Random();

    abstract int attack(int roll);
    public abstract String attackString(int roll);

    public int roll(int quantity, int sides){
        int value = 0;
        for (int i = 0; i < quantity; i++) {
            value += RNG.getInt(1, sides + 1);
        }
        return  value;
    }

    @Override
    public String toString() {
        return "This Zombie has " + arms + " arms and " + legs + " legs\nIt has " + baseHP + " health and a speed of " + speed;
    }

    public void setArms(int arms) {
        if (arms < MIN_ARMS || arms > MAX_ARMS) {
            throw new IllegalArgumentException("Invalid number of arms");
        }
        this.arms = arms;
    }

    public void setLegs(int legs) {
        if (arms < MIN_LEGS || arms > MAX_LEGS) {
            throw new IllegalArgumentException("Invalid number of legs");
        }
        this.legs = legs;
    }

    public abstract void setBaseHP(int baseHP);

    public abstract void setSpeed(int speed);
}
