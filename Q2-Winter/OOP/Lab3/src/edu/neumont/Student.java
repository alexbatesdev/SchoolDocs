package edu.neumont;

import java.util.ArrayList;

public class Student extends Human{
    private double gpa;
    private Staff advisor;
    private ArrayList<Course> enrolledCourses = new ArrayList<>();

    public Student(String name, int birthYear, double gpa, Staff advisor) {
        super(name, birthYear);
        this.gpa = gpa;
        this.advisor = advisor;
    }

    public Student(String name, int birthYear, double gpa) {
        super(name, birthYear);
        this.gpa = gpa;
    }

    protected double getGpa() {
        return gpa;
    }

    protected void setGpa(float gpa) {
        this.gpa = gpa;
    }

    protected void setAdvisor(Staff advisor) {
        this.advisor = advisor;
    }

    protected Staff getAdvisor() {
        return advisor;
    }

    protected ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    protected void addCourse(Course course) {
        this.enrolledCourses.add(course);
    }

    protected void remCourse(Course course) {
        for (int i = 0; i < this.enrolledCourses.size(); i++) {
            if (this.enrolledCourses.get(i).getName().equalsIgnoreCase(course.getName())) {
                this.enrolledCourses.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", gpa=" + gpa +
                ", advisor=" + advisor +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }
}