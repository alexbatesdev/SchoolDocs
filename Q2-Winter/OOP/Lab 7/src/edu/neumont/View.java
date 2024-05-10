package edu.neumont;

import java.text.SimpleDateFormat;

public class View {

    public void displayMenu() {
        System.out.println("1) create");
        System.out.println("2) view");
        System.out.println("3) search");
        System.out.println("4) quit");
    }

    public void displayEntry(Entry entry, SimpleDateFormat dateFormat) {
        String date_string = dateFormat.format(entry.getDate());
        System.out.print(date_string + ": ");
        System.out.println(entry.getLog());
    }

    public void displayQuery() {
        System.out.println("Create entry for today?");
        System.out.println("1) yes");
        System.out.println("2) I want another date");
    }
}
