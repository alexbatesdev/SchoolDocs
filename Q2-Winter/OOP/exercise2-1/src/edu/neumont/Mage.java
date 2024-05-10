package edu.neumont;

public class Mage extends Character {
    private double magic;

    public Mage(String name, double health, boolean isUndead, double magic) {
        super(name, health, isUndead);
        this.magic = magic;
    }

    public Mage(){
        super();
    }

    public void display() {
        System.out.println(magic);
        System.out.println(name);
        System.out.println(health);
        System.out.println(isUndead);
    }

    public double getMagic() {
        return magic;
    }

    public void setMagic(double magic) {
        this.magic = magic;
    }

    public void hit(double damage) {
        doDamage(damage);
    }

    @Override
    public String toString() {
        return super.toString() + " and has " + magic + " magic";
        /*return "Mage{" +
                "name='" + name + '\'' +
                ", health=" + String.format("%.2f", health) +
                ", isUndead=" + isUndead +
                ", magic=" + magic +
                '}';*/
    }
}
