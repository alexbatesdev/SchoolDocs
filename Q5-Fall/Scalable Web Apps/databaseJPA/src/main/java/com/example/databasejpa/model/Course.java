package com.example.databasejpa.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @Column(length = 250)
    private String courseCode;
    private String courseTitle;

    @OneToMany(mappedBy = "course")
    private List<pastEnrollment> pastEnrollments = new ArrayList<>();

    @ManyToMany
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseCode, String courseTitle) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
    }

    public Course() {

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseId) {
        this.courseCode = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String name) {
        this.courseTitle = name;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

}
