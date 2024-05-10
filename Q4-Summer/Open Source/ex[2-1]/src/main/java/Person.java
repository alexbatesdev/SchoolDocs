import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

public class Person implements Serializable {

    private String fullName;
    private String firstName;
    private String lastName;
    private String ssn;
    private String email;
    private String phone;
    final String regexPhone = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
    final String regexName = "(\\w+) (\\w+)";
    final String regexSSN = "[0-9]{3}-[0-9]{2}-[0-9]{4}";
    final String regexEmail = "\\w+@\\w*\\.[^,]+";

    private Matcher MatchyMatcher(String strLine, String strRegex) {
        Pattern p = null;
        Matcher m = null;
        p = Pattern.compile(strRegex);
        m = p.matcher(strLine);
        return m;
    }

    Person(String line){
        Matcher m = null;
        if (line != null) {
            m = this.MatchyMatcher(line, regexName);
            if (m.find()) {
                this.fullName = m.group();
                this.firstName = m.group(1);
                this.lastName = m.group(2);
            }
            //SSN
            m = this.MatchyMatcher(line, regexSSN);
            if (m.find()) {
                this.ssn = m.group();
            }
            //Email
            m = this.MatchyMatcher(line, regexEmail);
            if (m.find()) {
                this.email = m.group();
            }
            //Phone
            m = this.MatchyMatcher(line, regexPhone);
            if (m.find()) {
                this.phone = m.group();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Full Name: %s\r\nFirst Name: %s\r\nLast Name: %s\r\nSSN: %s\r\nEmail: %s\r\nPhone: %s\r\n"
                , this.fullName, this.firstName, this.lastName, this.ssn, this.email, this.phone);
    }
}
