package com.example.databasejpa.controller;

import com.example.databasejpa.model.Student;
import com.example.databasejpa.model.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    private StudentJpaRepository studentRepo;

    @PostMapping("")
    //@ResponseStatus(code= HttpStatus.CREATED)
    public int create(@RequestBody Student student) {
        return studentRepo.save(student).getStudentId();
    }

    @GetMapping("")
    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable int id) {
        Optional<Student> result = studentRepo.findById(id);
        return result.orElse(null);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student student) {
        Student existing = studentRepo.findById(id).get();
        existing.setName(student.getName());
        existing.setGpa(student.getGpa());
        return studentRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentRepo.deleteById(id);
    }

    @GetMapping("/searchByName/{name}")
    public List<Student> searchByName(@PathVariable String name) {
        return studentRepo.findByNameLike("%" + name + "%");
    }

    @GetMapping("/searchByGpa/{gpa}")
    public List<Student> searchByGpa(@PathVariable double gpa) {
        return studentRepo.findByGpaGreaterThan(gpa);
    }

    @GetMapping("/search/{name}/{gpa}")
    public List<Student> searchByGpa(@PathVariable String name, @PathVariable double gpa) {
        return studentRepo.findByNameLikeAndGpaGreaterThanEqual("%" + name + "%", gpa);
    }
}
