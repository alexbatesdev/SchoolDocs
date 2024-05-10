package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.Models.Person;
import com.example.fantasyapi.Onion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class SleepyPersonController {
    // Create
    @RequestMapping(path="", method=RequestMethod.POST)
    public void CreateEntry(@RequestBody Person item) {
        Onion.savePerson(item);
    }

    // Read
    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Person> findAllEntries() {
        return Onion.getPeople();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Person findEntryById(@PathVariable int id) {
        return Onion.getPerson(id);
    }

    // Update
    @RequestMapping(path="", method=RequestMethod.PUT)
    public void updateEntry(@RequestBody Person item) {
        Onion.updatePerson(item);
    }

    // Delete
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteEntry(@PathVariable int id) {
        Onion.deletePerson(id);
    }
}
