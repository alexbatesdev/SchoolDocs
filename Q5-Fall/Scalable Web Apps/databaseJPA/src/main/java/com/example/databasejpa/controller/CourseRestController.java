package com.example.databasejpa.controller;

import com.example.databasejpa.model.CourseJpaRepository;
import com.example.databasejpa.model.Course;
import com.example.databasejpa.model.Student;
import com.example.databasejpa.model.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    //copy Course and Student code here

    @Autowired
    private CourseJpaRepository courseRepo;

    @Autowired
    private StudentJpaRepository studentRepo;

    @PostMapping("")
    //@ResponseStatus(code= HttpStatus.CREATED)
    public String create(@RequestBody Course course) {
        return courseRepo.save(course).getCourseCode();
    }

    @GetMapping("")
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @GetMapping("/{courseCode}")
    public Course get(@PathVariable String courseCode) {
        Course result = courseRepo.findByCourseCode(courseCode);
        return result;
    }

    @PutMapping("/{courseCode}")
    public Course update(@PathVariable String courseCode, @RequestBody Course course) {
        Course existing = courseRepo.findByCourseCode(courseCode);
        existing.setCourseTitle(course.getCourseTitle());
        return courseRepo.save(existing);
    }

    @DeleteMapping("/{courseCode}")
    public void delete(@PathVariable String courseCode) {
        courseRepo.deleteByCourseCode(courseCode);
    }

    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    @PutMapping("/{courseCode}/enroll/{studentId}")
    public void addStudentToCourse(@PathVariable String courseCode, @PathVariable int studentId) {
        Course course = courseRepo.findByCourseCode(courseCode);
        Student student = studentRepo.findById(studentId).get();
        course.getEnrolledStudents().add(student);
        courseRepo.save(course);
    }
}
