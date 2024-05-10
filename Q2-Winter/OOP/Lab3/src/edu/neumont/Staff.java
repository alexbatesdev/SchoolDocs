package edu.neumont;

import java.util.ArrayList;

public class Staff extends Human{
    protected String jobTitle;
    protected ArrayList<Student> advisedStudents = new ArrayList<>();
    public Staff(String name, int birthYear, String jobTitle) {
        super(name, birthYear);
        this.jobTitle = jobTitle;
    }

    protected void addStudent(Student student) {
        this.advisedStudents.add(student);
    }

    protected void remStudent(Student student) {
        for (int i = 0; i < this.advisedStudents.size(); i++) {
            if (this.advisedStudents.get(i).getName().equalsIgnoreCase(student.getName())) {
                this.advisedStudents.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", jobTitle='" + jobTitle + '\'' +
                ", advisedStudents=" + advisedStudents +
                '}';
    }
}