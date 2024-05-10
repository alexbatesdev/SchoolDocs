package com.example.databasejpa.controller;

import com.example.databasejpa.model.InstructorJpaRepository;
import com.example.databasejpa.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructor")
public class InstructorRestController {

    //copy InstructorRestController.java and modify it to work with Instructor
    //use the InstructorJpaRepository to access the database
    //use the Instructor class to represent the data in the database

    @Autowired
    private InstructorJpaRepository instructorRepo;

    @PostMapping("")
    //@ResponseStatus(code= HttpStatus.CREATED)
    public int create(@RequestBody Instructor instructor) {
        return instructorRepo.save(instructor).getInstructorId();
    }

    @GetMapping("")
    public List<Instructor> getAll() {
        return instructorRepo.findAll();
    }

    @GetMapping("/{id}")
    public Instructor get(@PathVariable int id) {
        Optional<Instructor> result = instructorRepo.findById(id);
        return result.orElse(null);
    }

    @PutMapping("/{id}")
    public Instructor update(@PathVariable int id, @RequestBody Instructor instructor) {
        Instructor existing = instructorRepo.findById(id).get();
        existing.setName(instructor.getName());
        return instructorRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        instructorRepo.deleteById(id);
    }



}
