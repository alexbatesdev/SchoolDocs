package edu.neumont;

import java.util.Random;

public class Controller {
    public Controller() {
        Character character = new Character("Stubbs",100,true);
        character.doDamage(23);
        System.out.println(character);
        Character character2 = new Character("Stuart",120,false);
        System.out.println(character2);

        Random random = new Random();

        Mage man = new Mage("Gorg",100,false,50);
        man.hit(random.nextDouble(10,30));
        System.out.println(man);
        Mage man2 = new Mage("George",80,false,25);
        System.out.println(man2);

        Gremlin grayson = new Gremlin("Grayson",300,false,999);
        System.out.println(grayson);
        Gremlin tobie = new Gremlin("Tobie",200,true,666);
        System.out.println(tobie);
        System.out.println(new Gremlin());
    }
}
