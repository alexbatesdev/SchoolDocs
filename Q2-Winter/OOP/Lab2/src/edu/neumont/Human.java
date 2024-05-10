package edu.neumont;

public class Human {
    protected String name;
    protected int birthYear;

    public Human(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
}