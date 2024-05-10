import java.io.IOException;

public class Main {

    private static ActionJacktion oj = new ActionJacktion();

    public static void main(String[] args) {
//        try {
//            oj.save(new OneJournal(1, "Hello", "2020-01-01"));
//            oj.save(new OneJournal(2, "World", "2020-01-02"));
//            oj.save(new OneJournal(3, "!", "2020-01-03"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        oj.findAll();
//
//        GlobalStuff.entries.add(new OneJournal(1, "Hello", "2020-01-01"));
//        GlobalStuff.entries.add(new OneJournal(2, "World", "2020-01-02"));
//        GlobalStuff.entries.add(new OneJournal(3, "!", "2020-01-03"));
//
//        //iterate through and print out the entries
//        for (OneJournal entry : GlobalStuff.entries) {
//            System.out.println(entry);
//        }

        DataBased.selectPerson();
    }
}
