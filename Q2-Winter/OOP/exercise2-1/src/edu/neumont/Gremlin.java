package edu.neumont;

public class Gremlin extends Character {
    private double cxm; //ml

    public Gremlin(String name, double health, boolean isUndead, double cxm) {
        super(name, health, isUndead);
        this.cxm = cxm;
    }

    public Gremlin() {
        super();
    }

    public double getCxm() {
        return cxm;
    }

    public void setCxm(double cxm) {
        this.cxm = cxm;
    }

    @Override
    public String toString() {
        return "Gremlin{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", isUndead=" + isUndead +
                ", cxm=" + cxm +
                '}';
    }
}
