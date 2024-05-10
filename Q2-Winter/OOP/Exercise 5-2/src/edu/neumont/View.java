package edu.neumont;

import java.util.ArrayList;

public class View {
    public void displayMenu() {
        System.out.println("1) Add Vehicle");
        System.out.println("2) Brake Vehicles");
        System.out.println("3) Accelerate Vehicles");
        System.out.println("4) Set vehicles speed");
        System.out.println("5) Quit");
    }

    public void displayVehicles(ArrayList<IVehicle> vehicles) {
        System.out.println("-------------");
        for (var vehicle: vehicles) {
            System.out.println(vehicle);
        }
        System.out.println("-------------");
    }
}
