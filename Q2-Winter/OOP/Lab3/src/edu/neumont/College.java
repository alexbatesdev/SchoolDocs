package edu.neumont;

import java.util.ArrayList;

public class College {
    enum PersonType {
        FACULTY,
        STAFF,
        STUDENT
    }

    enum Menu {
        ADD,
        REMOVE,
        VIEW,
        QUIT
    }
    String name;

    ArrayList<Faculty> faculty = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Staff> staff = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();

    public College(String name) {
        this.name = name;
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("1) Add");
            System.out.println("2) Remove");
            System.out.println("3) View");
            System.out.println("4) Quit");

            int selection = Console.getInt("Enter selection: ", 1, 4);
            Menu menu = Menu.values()[selection-1];
            switch (menu) {
                case ADD:
                    add();
                    break;
                case REMOVE:
                    remove();
                    break;
                case VIEW:
                    view();
                    break;
                case QUIT:
                    System.out.println("ok bye");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, valid inputs are numbers 1-4");
                    break;
            }
        }
    }

    private void add() {
        PersonType type = getPersonType();
        String name = Console.getString("Enter name: ");
        int birthYear = Console.getInt("Enter birth year: ");

        switch (type) {
            case FACULTY:
                int officeNum = Console.getInt("Enter office number: ");
                String officeHours = Console.getString("Enter office hours: (Example: 12-5)");
                boolean fulltime = Console.getString("Fulltime (y/n): ").equalsIgnoreCase("y");
                faculty.add(new Faculty(name, birthYear, officeNum,officeHours));
                break;
            case STAFF:
                String jobTitle = Console.getString("Enter job title: ");
                staff.add(new Staff(name, birthYear, jobTitle));
                break;
            case STUDENT:
                float gpa = Console.getFloat("Enter gpa: ");
                students.add(new Student(name, birthYear, gpa));
                break;
        }
    }

    private void remove() {
        PersonType type = getPersonType();

        String name = Console.getString("Enter name: ");
        switch (type) {
            case FACULTY:
                for (Faculty faculty: this.faculty) {
                    if (faculty.compareName(name)) {
                        this.faculty.remove(faculty);
                        System.out.println("Item removed");
                        break;
                    }
                }
                break;
            case STAFF:
                for (Staff staff: this.staff) {
                    if (staff.compareName(name)) {
                        this.staff.remove(staff);
                        System.out.println("Item removed");
                        break;
                    }
                }
                break;
            case STUDENT:
                for (Student student: this.students) {
                    if (student.compareName(name)) {
                        this.students.remove(student);
                        System.out.println("Item removed");
                        break;
                    }
                }
                break;
        }
    }

    private void view() {
        PersonType type = getPersonType();
        switch (type) {
            case FACULTY:
                for (Faculty faculty: this.faculty) {
                    System.out.println(faculty);
                }
                break;
            case STAFF:
                for (Staff staff: this.staff) {
                    System.out.println(staff);
                }
                break;
            case STUDENT:
                for (Student student: this.students) {
                    System.out.println(student);
                }
                break;
        }
    }

    private PersonType getPersonType() {
        System.out.println("1) Faculty");
        System.out.println("2) Staff");
        System.out.println("3) Student");

        int selection = Console.getInt("Enter Selection: ", 1, 3);
        PersonType type = PersonType.values()[selection-1];

        return type;
    }
}