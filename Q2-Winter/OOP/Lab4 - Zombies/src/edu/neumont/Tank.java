package edu.neumont;

public class Tank extends Zombie{
    static final int MIN_HP = 45;
    static final int MAX_HP = 70;
    static final int MIN_SPEED = 10;
    static final int MAX_SPEED = 20;
    static final int MIN_DMOD = 10;
    static final int MAX_DMOD = 20;
    //baseHP 45-70
    //speed 10-20
    //damageModifier 10-20

    private int damageModifier;

    public Tank() {
        setArms(RNG.getInt(MIN_ARMS, MAX_ARMS+1));
        setLegs(RNG.getInt(MIN_LEGS, MAX_LEGS+1));
        setBaseHP(RNG.getInt(MIN_HP, MAX_HP+1));
        setSpeed(RNG.getInt(MIN_SPEED, MAX_SPEED+1));
        setDamageModifier(RNG.getInt(MIN_DMOD, MAX_DMOD+1));
    }

    public void setDamageModifier(int damageModifier) {
        if (damageModifier < MIN_DMOD || damageModifier > MAX_DMOD) {
            throw new IllegalArgumentException("Invalid damage modifier");
        }
        this.damageModifier = damageModifier;
    }

    @Override
    int attack(int a_roll) {
        if (a_roll == 20 || a_roll == 19) {
            return (roll(3, 6) + damageModifier) * 3;
        } else if (a_roll >= 10 && a_roll <= 19) {
            return roll(3, 6) + damageModifier;
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
        return super.toString() + "\n This zombie is a tank class, this zombie is much slower than a regular zombie but it is much more durable and has an attack bonus of " + damageModifier;
    }
}
