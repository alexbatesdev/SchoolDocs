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

    public boolean compareName(String name) {
        return this.name.equalsIgnoreCase(name);
    }
    public int getBirthYear() {
        return birthYear;
    }
}