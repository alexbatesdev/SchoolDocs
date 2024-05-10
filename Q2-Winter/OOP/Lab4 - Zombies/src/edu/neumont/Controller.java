package edu.neumont;

public class Controller {
    private ZombieGenerator generator = new ZombieGenerator();

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("1) make a zombie");
            System.out.println("2) make # zombies");
            System.out.println("3) make a random amount of zombies");
            System.out.println("4) quit");

            int selection = Console.getInt("Enter selection: ", 1, 4);

            switch (selection) {
                case 1:
                    generator.generate(1);
                    break;
                case 2:
                    int numZ = Console.getInt("Enter selection: ", 1, 999);
                    generator.generate(numZ);
                    break;
                case 3:
                    generator.generate(RNG.getInt(1, 11));
                    break;
                case 4:
                    running = false;
                    break;
            }
            generator.display();
        }
    }
}
