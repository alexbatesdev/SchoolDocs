package com.example.databasejpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    private String name;
    private double gpa;

    @OneToMany(mappedBy = "student")
    private List<pastEnrollment> pastEnrollments = new ArrayList<>();

    @ManyToMany(mappedBy = "enrolledStudents")
    @JsonIgnore
    private List<Course> currentEnrollments = new ArrayList<>();

    public List<pastEnrollment> getPastEnrollments() {
        return pastEnrollments;
    }

    public void setPastEnrollments(List<pastEnrollment> pastEnrollments) {
        this.pastEnrollments = pastEnrollments;
    }

    public List<Course> getCurrentEnrollments() {
        return currentEnrollments;
    }

    public void setCurrentEnrollments(List<Course> currentEnrollments) {
        this.currentEnrollments = currentEnrollments;
    }

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public Student() {

    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
