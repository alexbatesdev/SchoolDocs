package edu.neumont;

import java.util.ArrayList;

public class Course {
    private String name;
    private Faculty instructor;
    private ArrayList<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Faculty instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public void setInstructor(Faculty instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    protected void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", instructor=" + instructor.name +
                ", students=" + students +
                '}';
    }
}
