package edu.neumont;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private ArrayList<IVehicle> vehicles = new ArrayList<>();
    private View view = new View();

    public void run() {



        boolean running = true;
        while(running) {
            view.displayMenu();

            int selection = Console.getInt("Enter selection: ", 1, 5);

            switch(selection) {
                case 1:
                    int r = new Random().nextInt(4);
                    switch(r) {
                        case 0:
                            vehicles.add(new Car());
                            break;
                        case 1:
                            vehicles.add(new Boat());
                            break;
                        case 2:
                            vehicles.add(new SpeedBoat());
                            break;
                        case 3:
                            vehicles.add(new SportsCar());
                            break;
                    }
                    break;
                case 2:
                    for (var vehicle: vehicles) {
                        vehicle.brake();
                    }
                    break;
                case 3:
                    for (var vehicle: vehicles) {
                        vehicle.accelerate();
                    }
                    break;
                case 4:
                    int s = Console.getInt("How fast?", 0, 200);
                    for (var vehicle: vehicles) {
                        vehicle.setSpeed(s);
                    }
                    break;
                case 5:
                    running = false;
                    break;
            }

            if (running) {
                view.displayVehicles(vehicles);
            }
        }
    }
}
