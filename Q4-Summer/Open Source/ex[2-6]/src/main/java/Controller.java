import java.lang.reflect.Array;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Controller implements RegexUtility {

    //if (strToSearch.matches(strRegex))

    private Matcher RegexMatch(String strLine, String strRegex) {
        Pattern p = null;
        Matcher m = null;
        p = Pattern.compile(strRegex);
        m = p.matcher(strLine);
        return m;
    }

    @Override
    public boolean isValidHumanName(String name) {
        String regexToMatch = "([A-Z]\\w*\\. )?([A-Z]\\w* {0,1}){1,}";
        Matcher m = RegexMatch(name, regexToMatch);
        if (m.find()) {
            if (m.group(0).equals(name)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isValidEmailAddress(String email) {
        String regexToMatch = "^(?=[A-z])[A-z0-9_.]*@(?=[^0-9])[A-z0-9]*?\\.[A-z]{3,4}$";
        Matcher m = RegexMatch(email, regexToMatch);
        if (m.find()) {
            if (m.group(0).equals(email)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isValidPhoneNumber(String phone) {
        String regexToMatch = "((\\d{1,2})-)?(\\d{3})-(\\d{3})-(\\d{4})";
        Matcher m = RegexMatch(phone, regexToMatch);
        if (m.find()) {
            if (m.group(0).equals(phone)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isValidSSN(String ssn) {
        String regexToMatch = "[0-9]{3}-[0-9]{2}-[0-9]{4}";
        Matcher m = RegexMatch(ssn, regexToMatch);
        if (m.find()) {
            if (m.group(0).equals(ssn)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean validatePasswordComplexity(String password, int minLength, int minUpper, int minLower, int minNumeric, int minSymbols) {
        String regexToMatch = String.format("^.*(?=.*([a-z].*){%d,})(?=.*([0-9].*){%d,})(?=.*(\\W.*){%d,})(?=.*([A-Z].*){%d,}).{%d}.*$", minLower, minNumeric, minSymbols, minUpper, minLength);
        Matcher m = RegexMatch(password, regexToMatch);
        if (m.find()) {
            if (m.group(0).equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String[] getHTMLTagsContents(String html, String tagName) {
        String regexToMatch = String.format("<%s.*>(.*)</%s>", tagName, tagName);//<%s.*>(.*)</%s>
        Matcher m = RegexMatch(html, regexToMatch); //TODO get help with this -> The matcher is stopping at the first match and I have no clue why
        ArrayList<String> wordlist = new ArrayList<>();
        while  (m.find()) {
            wordlist.add(m.group(1));
        }
        String[] s = new String[wordlist.size()];
        for (int j = 0; j < s.length; j++) {
            s[j] = wordlist.get(j);
        }
        return s;
    }

    @Override
    public String[] getHTMLLinkURL(String html) {
        String regexToMatch = String.format("<a\\s.*href=\\\"(.*)\\\">(.*)</a>");
        Matcher m = RegexMatch(html, regexToMatch);
        ArrayList<String> wordlist = new ArrayList<>();
        while  (m.find()) {
            wordlist.add(m.group(1));
        }
        String[] s = new String[wordlist.size()];
        for (int j = 0; j < s.length; j++) {
            s[j] = wordlist.get(j);
        }
        return s;
    }

    public static void test(String input) {
        Pattern word = Pattern.compile("\\w{10,}");
        Matcher m = word.matcher(input);
        ArrayList<String> wordList = new ArrayList<>();

        while (m.find()) {
            wordList.add(m.group());
        }
        System.out.println(wordList);
    }
}
