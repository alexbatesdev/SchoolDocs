package edu.neumont;

public abstract class Animal {
    protected String name;
    protected int eyes;
    protected int lifespan;
    protected boolean isAlive;



    public abstract void speak();
    public abstract void eat();



    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", eyes=" + eyes +
                ", lifespan=" + lifespan +
                ", isAlive=" + isAlive +
                '}';
    }
}
