package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.DatabaseConnect;
import com.example.fantasyapi.Models.Confirmation;
import com.example.fantasyapi.Models.Devil;
import com.example.fantasyapi.Models.Location;
import com.example.fantasyapi.Models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SQL")
public class SQLRestController {
    // Login
    @RequestMapping(path="/login", method=RequestMethod.GET)
    public void login() {

    }

    /* Devil */
    // Create
    @PostMapping(path="/devil", produces = "application/json")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Confirmation CreateDevil(@RequestBody Devil item) {
        DatabaseConnect.insertDevil(item);
        return new Confirmation("Devil created", "post", item);
    }

    // Read
    @GetMapping(path="/devil")
    public List<Devil> findAllDevils() {
        return DatabaseConnect.selectAllDevils();
    }

    @GetMapping(path="/devil/{id}")
    public Devil findDevilById(@PathVariable int id) {
        return DatabaseConnect.selectDevil(id);
    }

    // Update
    @PutMapping(path="/devil")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation updateDevil(@RequestBody Devil item) {
        DatabaseConnect.updateDevil(item);
        return new Confirmation("Devil updated", "put", item);
    }

    // Delete
    @DeleteMapping(path="/devil/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation deleteDevil(@PathVariable int id) {
        DatabaseConnect.deleteDevil(id);
        return new Confirmation("Devil deleted", "delete", id);
    }

    /* Person */
    // Create
    @PostMapping(path="/person")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Confirmation CreatePerson(@RequestBody Person item) {
        DatabaseConnect.insertPerson(item);
        return new Confirmation("Person created", "post", item);
    }

    // Read
    @GetMapping(path="/person")
    public List<Person> findAllPeople() {
        return DatabaseConnect.selectAllPeople();
    }

    @GetMapping(path="/person/{id}")
    public Person findPersonById(@PathVariable int id) {
        return DatabaseConnect.selectPerson(id);
    }

    // Update
    @PutMapping(path="/person")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation updatePerson(@RequestBody Person item) {
        DatabaseConnect.updatePerson(item);
        return new Confirmation("Person updated", "put", item);
    }

    // Delete
    @DeleteMapping(path="/person/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation deletePerson(@PathVariable int id) {
        DatabaseConnect.deletePerson(id);
        return new Confirmation("Person deleted", "delete", id);
    }

    /* Location */
    // Create
    @PostMapping(path="/location")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Confirmation CreateLocation(@RequestBody Location item) {
        DatabaseConnect.insertLocation(item);
        return new Confirmation("Location created", "post", item);
    }

    // Read
    @GetMapping(path="/location")
    public List<Location> findAllLocations() {
        return DatabaseConnect.selectAllLocations();
    }

    @GetMapping(path="/location/{id}")
    public Location findLocationById(@PathVariable int id) {
        return DatabaseConnect.selectLocation(id);
    }

    // Update
    @PutMapping(path="/location")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation updateLocation(@RequestBody Location item) {
        DatabaseConnect.updateLocation(item);
        return new Confirmation("Location updated", "put", item);
    }

    // Delete
    @DeleteMapping(path="/location/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Confirmation deleteLocation(@PathVariable int id) {
        DatabaseConnect.deleteLocation(id);
        return new Confirmation("Location deleted", "delete", id);
    }
}
