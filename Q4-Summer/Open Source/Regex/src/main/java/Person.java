public class Person {
    private String phoneNum;

    public boolean setPhoneNum(String phoneNum) {

        if (phoneNum.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
            System.out.println("Accepted! Valid number");
            this.phoneNum = phoneNum;
            return true;
        } else {
            System.out.println("Rejected! Invalid number");
            return false;
        }

    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
