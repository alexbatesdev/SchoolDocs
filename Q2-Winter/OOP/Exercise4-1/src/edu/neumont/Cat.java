package edu.neumont;

public class Cat extends Animal {

    @Override
    public void speak() {
        System.out.println("Also Meow");
    }

    @Override
    public void eat() {
        System.out.println("NomNom but Cat");
    }
}
