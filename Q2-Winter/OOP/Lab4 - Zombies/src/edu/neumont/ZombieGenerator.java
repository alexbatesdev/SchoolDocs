package edu.neumont;

import java.util.ArrayList;

public class ZombieGenerator {
    enum ZombieType {
        WALKER,
        RUNNER,
        TANK
    }
    private ArrayList<Zombie> horde = new ArrayList<>();

    public void generate(int number) {
        horde.clear();
        for (int i = 0; i < number; i++) {
            ZombieType type = ZombieType.values()[RNG.getInt(0,3)];
            horde.add(createZombie(type));
        }
    }

    public void display() {
        for (var zombie: horde) {
            int attackRoll = zombie.roll(1, 20);
            int attackValue = zombie.attack(attackRoll);
            String result = zombie.toString() + " " + zombie.attackString(attackRoll) + " attack value =" + attackValue;
            System.out.println(result);
        }
    }

    private Zombie createZombie(ZombieType type) {
        Zombie zombie = null;
        switch (type) {
            case WALKER:
                zombie = new Walker();
                break;
            case RUNNER:
                zombie = new Runner();
                break;
            case TANK:
                zombie = new Tank();
                break;
        }

        return zombie;
    }
    //create attack for all subclasses
    //create random generator
    //set health and speed for all classes using random
    //set arms and legs for all classes using random
    /* Ask maple about getters and setters stuffs */
    //make random and manual constructors for all subclasses

}
