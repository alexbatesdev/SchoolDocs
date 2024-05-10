package edu.neumont;

import java.text.SimpleDateFormat;
import java.util.Date;

//Your application should allow the user to:
//Create new journal entries
//View an entry by date
//Search for entries given a range of dates
//Your application must persist (save and load so they are not lost) journal entries to disk.
//Your application should utilize MVC, should maintain proper encapsulation, and should respect single-responsibility for classes.
//Create classes and methods as needed to follow these guidelines.
//You should also ensure that:
//the user cannot enter invalid dates
//The user can create a journal entry for today without specifying a date
//The user can create a journal entry for any day in the past by specifying a date
//The user can append to an existing journal entry for a given date
//The user can search for journal entries by specifying a date interval (start and end) and the results are displayed
//When displaying search results, display the date and a "summary" of the text.  A summary means the start of the text up to 45 characters but ending on a word boundary - don't cut a word, followed by an ellipsis (...).  For example:  "Today my OOP class was awesome!  I couldn't..."
//When searching, the user is told if there are no journal entries in the selected date range
//The user is able to move back and forth between entering new journal entries and viewing old ones
public class Main {

    public static void main(String[] args) {
        Journal journal = new Journal();
        try {
            journal.load();
            journal.run();
        } catch (Exception e) {
            System.out.println("oops" + e);
        }
    }
}
