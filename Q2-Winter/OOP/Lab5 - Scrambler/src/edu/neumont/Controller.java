package edu.neumont;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    View view = new View();
    String userString = "Alex";
    ArrayList<IStringEncryptable> encryptors = new ArrayList<>();

    public void run() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            int selection = Console.getInt("Enter selection: ", 1, 8);

            switch (selection) {
                case 1:// enter string
                    userString = Console.getString("Enter string: ");
                    break;
                case 2://view string
                    System.out.println("userString = " + userString);
                    break;
                case 3://add encryption
                    view.displayEncryptorsMenu();
                    selection = Console.getInt("Enter selection: ", 1, 4);
                    switch (selection) {
                        case 1:
                            encryptors.add(new Doubler());
                            break;
                        case 2:
                            encryptors.add(new Cutter());
                            break;
                        case 3:
                            encryptors.add(new VowelReplacer());
                            break;
                        case 4:
                            encryptors.add(new Custom());
                            break;
                    }
                    break;
                case 4://display encryptions
                    view.displayEncryptors(encryptors);
                    break;
                case 5://clear encryptions
                    encryptors.clear();
                    break;
                case 6://encrypt string
                    for (var encryptor : encryptors) {
                        userString = encryptor.encrypt(userString);
                        System.out.println("String3: " + userString);
                    }
                    break;
                case 7://decrypt string
                    /*
                    for (int i = encryptors.size(); i >= 0; i--) {
                        userString = encryptors.get(i).decrypt(userString);
                    }
                    */
                    Collections.reverse(encryptors);
                    for (var encryptor : encryptors) {
                        userString = encryptor.decrypt(userString);
                        System.out.println("String: " + userString);
                    }
                    Collections.reverse(encryptors);
                    break;
                case 8://quit
                    running = false;
                    break;
            }
        }
    }
}
