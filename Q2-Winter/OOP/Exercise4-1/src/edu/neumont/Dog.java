package edu.neumont;

public class Dog extends Animal {

    @Override
    public void speak() {
        System.out.println("Meow");
    }

    @Override
    public void eat() {
        System.out.println("NomNom but dog");
    }
}
