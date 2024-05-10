package edu.neumont;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Journal {
    View view = new View();
    ArrayList<Entry> entries = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    static final String DIRECTORY = "src/resources/";

    public void run() throws Exception {
        boolean running = true;
        while (running) {

            view.displayMenu();
            int selection = Console.getInt("enter selection: ", 1, 4);

            switch (selection) {
                case 1: // create
                    view.displayQuery();
                    int dateSelection = Console.getInt("enter selection: ");
                    Date date = null;
                    if (dateSelection == 1) {
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        dateFormat.setLenient(false);
                        date = dateFormat.parse(dateFormat.format(now));
                    } else if (dateSelection == 2) {
                        String dateString = Console.getDateString("enter date (mm-dd-yyyy): ", dateFormat);
                        date = dateFormat.parse(dateString);
                    }
                    boolean append = false;
                    Entry entry = null;
                    for (Entry oldEntry : entries) {
                        System.out.println(oldEntry.getDate() + " =?= " + date);
                        if (oldEntry.getDate().compareTo(date) == 0) {
                            entry = appendEntry(oldEntry);
                            append = true;
                            System.out.println("POPOPOPO");
                        }
                    }
                    if (!append) entry = createEntry(date);
                    saveEntry(entry);
                    addEntry(entry);
                    break;
                case 2: // view
                    view();
                    break;
                case 3: // search
                    String startString = Console.getDateString("enter start date (mm-dd-yyyy): ", dateFormat);
                    Date start = dateFormat.parse(startString);
                    String endString = Console.getDateString("enter end date (mm-dd-yyyy): ", dateFormat);
                    Date end = dateFormat.parse(endString);
                    search(start, end);
                    break;
                case 4: // quit
                    running = false;
                    break;
            }
        }
    }

    void search(Date startDate, Date endDate) {
        boolean found = false;
        for (Entry entry : entries) {
            if (entry.getDate().compareTo(startDate) >= 0 && entry.getDate().compareTo(endDate) <= 0) {
                view.displayEntry(entry, dateFormat);
                found = true;
            }
        }

        if (!found) System.out.println("no entries found...");
    }

    void view() {
        for (Entry entry : entries) {
            view.displayEntry(entry, dateFormat);
        }
    }

    Entry createEntry(Date date) {

        String log = Console.getString("enter journal entry: ");
        Entry entry = new Entry(date, log);

        return entry;
    }

    Entry appendEntry(Entry oldEntry) {
        String oldLog = oldEntry.getLog();
        String log = Console.getString("enter journal appendment: ");
        Entry entry = new Entry(oldEntry.getDate(), oldLog + " " + log);

        return entry;
    }

    void addEntry(Entry entry) {
        entries.add(entry);
    }

    void saveEntry(Entry entry)  throws Exception {
        String date_string = dateFormat.format(entry.getDate());
        String filename = DIRECTORY + date_string + ".txt";
        FileOutputStream output = new FileOutputStream(filename);
        IOStream.writeToStream(entry.getLog(), output);
        output.close();
    }

    void load() throws Exception {
        File directory = new File(DIRECTORY);
        for (var file : directory.listFiles()) {
            directory.listFiles();
            FileInputStream input = new FileInputStream(file);
            String fileName = file.getName();
            String dateString = fileName.substring(0, fileName.indexOf('.'));

            Date date = dateFormat.parse(dateString);
            String log = IOStream.readToString(input);

            Entry entry = new Entry(date, log);
            addEntry(entry);
        }
    }
}
