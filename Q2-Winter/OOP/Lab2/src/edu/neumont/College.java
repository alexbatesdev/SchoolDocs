package edu.neumont;

import java.util.ArrayList;

public class College {
    ArrayList<Faculty> faculty = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Staff> staff = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();

    public void create() {
        staff.add(new Staff("Reed", 2000, "President"));
        staff.add(new Staff("Canter", 1995, "HR"));

        faculty.add(new Faculty("Maple", 1999, 317, "12-5"));
        faculty.add(new Faculty("Jim", 1980, 315, "12-5"));
        faculty.add(new Faculty("Jerry", 1974, 317, "12-5"));

        courses.add(new Course("CSC150", faculty.get(0)));
        courses.add(new Course("BIT105", faculty.get(1)));

        faculty.get(0).addCourse(courses.get(0));
        faculty.get(1).addCourse(courses.get(1));

        students.add(new Student("Han", 1990, 3.8, staff.get(0)));
        students.add(new Student("Alex", 2002, 4.0, staff.get(1)));
        students.add(new Student("Bingus", 2003, 4.0, staff.get(0)));

        courses.get(0).addStudent(students.get(0));
        courses.get(0).addStudent(students.get(1));
        courses.get(1).addStudent(students.get(2));

        for (Course course: courses) {
            System.out.println(course);
        }
    }
}