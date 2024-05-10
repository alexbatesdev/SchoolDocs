package edu.neumont;

public class RX7 {
    private final String COLOR;
    private final int YEAR;
    private final String GENERATION;
    private float horsePower;
    private boolean ownedByAlex;
    private int gas;
    private final int CAR_MAX_GAS = 12;


    RX7() {
        COLOR = "red";
        YEAR = 1983;
        GENERATION = "fb";
        horsePower = 100.0f;
        ownedByAlex = false;
        gas = 12;
    }

    public RX7(String color, int year, String generation) {
        this.COLOR = color;
        this.YEAR = year;
        this.GENERATION = generation;
    }

    public RX7(String color, int year) {
        this.COLOR = color;
        this.YEAR = year;
        this.GENERATION = "unknown";
    }

    @Override
    public String toString() {
        return "RX7:\n" +
                "color='" + COLOR + '\'' + "\n" +
                "year=" + YEAR + "\n" +
                "generation='" + GENERATION + '\'' + "\n" +
                "horsePower=" + horsePower + "\n" +
                "ownedByAlex=" + ownedByAlex + "\n" +
                "gas=" + gas;
    }

    public int getYear() {
        return YEAR;
    }

    public void setGas(int gas) {
        if (gas >= 0 && gas <= CAR_MAX_GAS) {
            this.gas = gas;
        }
    }

    public int getGas() {
        return gas;
    }

    public void setOwnedByAlex(boolean ownedByAlex) {
        this.ownedByAlex = ownedByAlex;
    }
}
