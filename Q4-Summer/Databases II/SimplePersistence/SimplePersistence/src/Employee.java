public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int hireYear;

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n"
                + "id: " + id + "\n"
                + "hireYear: " + hireYear + "\n";
    }

    public Employee(String id, String firstName, String lastName, String hireYear) {
        this.id = Integer.parseInt(id.trim());
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireYear = Integer.parseInt(hireYear.trim());
    }
}
