import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        ArrayList<String> aryLines = readLines();
        ArrayList<Person> aryPersons = createPersons(aryLines);

        for (Person p : aryPersons) {
            System.out.println(p);
        }
    }

    public static ArrayList<Person> createPersons(ArrayList<String> lines) {
        ArrayList<Person> persons = new ArrayList<Person>();

        for (String line : lines) {
            Person p = new Person(line);
            persons.add(p);
            System.out.println(p.toString());
        }
        return persons;
    }

    public static ArrayList<String> readLines() {
        String filename = "data/people.to.regex.csv";
        File f = new File(filename);
        ArrayList<String> lines = new ArrayList<>();
        if (f.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(f));
                String line = br.readLine();

                while (line != null) {
                    System.out.println(line);
                    line = br.readLine();
                    lines.add(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }
        return lines;
    }
}
