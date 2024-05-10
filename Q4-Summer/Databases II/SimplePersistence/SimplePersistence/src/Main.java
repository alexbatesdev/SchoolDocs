import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintEmployees("SimplePersistence_data/people/simple");
    }
    static void PrintPeopleDetails(String path) throws IOException {
        File peopleFolder = new File(path);
        File[] peopleFiles = peopleFolder.listFiles();
        for(int i = 0; i < peopleFiles.length; i++){
            System.out.println(readFromTextFile(peopleFiles[i].toString()));
        }
    }

    static void PrintEmployees(String path) throws IOException {
        File peopleFolder = new File(path);
        File[] peopleFiles = peopleFolder.listFiles();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < peopleFiles.length; i++) {
            String[] stuff = readFromTextFile(peopleFiles[i].toString()).split(", ");
            employees.add(new Employee(stuff[0], stuff[1], stuff[2], stuff[3]));
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).toString());
        }
//        Takes a path parameter
//        Iterates over each file in the given path
//        Creates an Employee Object that represents the data in each file
//        Prints the toString of each created Employee Object

    }

    public static String readFromTextFile(String readPath) throws IOException {
        InputStream fileIn = new FileInputStream(readPath);
        BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));
        String line = "";
        try {
            while (in.ready()) {
                line += in.readLine() + "\r\n";
            }
        } finally {
            fileIn.close();
        }
        return line;

    }
}
