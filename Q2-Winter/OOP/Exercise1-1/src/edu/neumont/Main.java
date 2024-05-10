package edu.neumont;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        RX7 myBaby = new RX7("black", 1985, "fb");
        myBaby.setGas(7);
        myBaby.setOwnedByAlex(true);
        System.out.println(myBaby);

        RX7 otherCar = new RX7("red", (int) Math.floor(Math.random() * (2002 - 1978 + 1) + 1978));
        System.out.println(otherCar);
    }
}
