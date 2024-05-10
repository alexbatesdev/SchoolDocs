package edu.neumont;

public class Character {
    protected String name;
    protected double health;
    protected boolean isUndead;

    public Character() {
        System.out.println("Character constructor");
    }

    public Character(String name, double health, boolean isUndead) {
        this.name = name;
        this.health = health;
        this.isUndead = isUndead;
    }

    protected void doDamage(double damage) {
        health -= damage;
    }

    @Override
    public String toString() {
        return "The caharcter is " + name + " with " + health + " health and is " + ((isUndead == true) ? "undead" : "alive");
        /*return "Character{" +
                "name='" + name + '\'' +
                ", health=" + String.format("%.2f", health) +
                ", isUndead=" + isUndead +
                '}';*/
    }
}