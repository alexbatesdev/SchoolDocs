package edu.neumont;

public class Main {

    public static void main(String[] args) {
        //Animal a = new Animal();
        //a.speak();

        Animal c = new Cat();
        c.speak();

        Animal snoop = new Dog();
        snoop.speak();

        System.out.println("Spinto");
        Animal[] animals = {new Cat(), new Dog(), new Cat(), new Cat()};

        for (var animal: animals) {
            animal.speak();animal.eat();
        }
    }
}
