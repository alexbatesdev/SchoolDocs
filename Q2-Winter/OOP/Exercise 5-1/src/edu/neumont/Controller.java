package edu.neumont;

public class Controller {
    Car car = new Car();
    Boat boat = new Boat();

    public void run() {
        boolean running = true;
        while(running) {
            System.out.println("1) Accelerate Car");
            System.out.println("2) Brake Car");
            System.out.println("3) Accelerate Boat");
            System.out.println("4) Brake Boat");
            System.out.println("5) Quit");

            int selection = Console.getInt("Enter selection: ", 1, 5);

            switch(selection) {
                case 1:
                    car.Accelerate();
                    break;
                case 2:
                    car.Brake();
                    break;
                case 3:
                    boat.Accelerate();
                    break;
                case 4:
                    boat.Brake();
                    break;
                case 5:
                    running = false;
                    break;
            }

            if (running) {
                System.out.println("Car speed: " + car.getSpeed());
                System.out.println("Water Car speed: " + boat.getSpeed());
            }
        }
    }
}
