import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class Program {
    public static void main(String[] args) {
        //RegExStuff();

        Person p = new Person();
        p.setPhoneNum("801-568-4414");
    }

    public static void RegExStuff() {
        String strToSearch = "801-568-4414";
        String strRegex = "[0-9]{3}-[0-9]{3}-[0-9]{4}";

        if (strToSearch.matches(strRegex)) {
            System.out.println("Matches");
        } else {
            System.out.println("Doesn't");
        }

        //strToSearch = strToSearch.replaceAll("[0-9]{3}", "X");
        //System.out.println(strToSearch);
        strRegex = "[0-9]{3,4}";
        RegExRunner(strToSearch, strRegex);
    }

    public static void RegExRunner(String strOrig, String strRegex) {
        System.out.println("-->Searching '" + strOrig + "' regex: '" + strRegex + "'");

        Pattern p = Pattern.compile(strRegex);
        Matcher m = p.matcher(strOrig);

        int intMatchCount = 0;

        while (m.find()) {
            intMatchCount += 1;
            System.out.printf("Match# %d = '%s'", intMatchCount, m.group());
            System.out.println();
            System.out.printf("Position start: %d, end: %d", m.start(), m.end());
            System.out.println();
        }

        System.out.println("--> There are " + intMatchCount + " total matches.");
    }
}
