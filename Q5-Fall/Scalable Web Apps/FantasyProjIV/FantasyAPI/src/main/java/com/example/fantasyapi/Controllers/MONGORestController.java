package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.Models.Devil;
import com.example.fantasyapi.Models.Location;
import com.example.fantasyapi.Models.Person;
import com.example.fantasyapi.Models.User;
import com.example.fantasyapi.Repos.MongoDevilRepo;
import com.example.fantasyapi.Repos.MongoLocationRepo;
import com.example.fantasyapi.Repos.MongoPersonRepo;
import com.example.fantasyapi.Repos.MongoUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/MONGO")
public class MONGORestController {

    @Autowired
    MongoUserRepo mongoUserRepo;

    @Autowired
    MongoPersonRepo mongoPersonRepo;

    @Autowired
    MongoDevilRepo mongoDevilRepo;

    @Autowired
    MongoLocationRepo mongoLocationRepo;

    // Login
    @RequestMapping(path="/login", method= RequestMethod.GET)
    public void login() {

    }

    @RequestMapping(path="/user", method= RequestMethod.POST)
    public void user(@RequestBody User user) {
        List<User> users = mongoUserRepo.findAll();
        int largestId = 0;
        for (User u : users) {
            if (u.getUserId() > largestId) {
                largestId = u.getUserId();
            }
            if (u.getUsername().equals(user.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        user.userId = largestId + 1;
        mongoUserRepo.save(user);
    }

    /* Person */
    // Create
    @RequestMapping(path="/person", method= RequestMethod.POST)
    public void CreatePerson(@RequestBody Person item) {
        List<Person> people = mongoPersonRepo.findAll();
        for (Person person : people) {
            if (person.getPersonId() == item.getPersonId()) {
                return;
            }
        }
        mongoPersonRepo.save(item);
    }

    // Read
    @RequestMapping(path="/person", method=RequestMethod.GET)
    public List<Person> findAllPeople() {
        return mongoPersonRepo.findAll();
    }

    @RequestMapping(path="/person/{id}", method=RequestMethod.GET)
    public Person findPersonById(@PathVariable String id) {
        int personId;
        try {
            personId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return mongoPersonRepo.findByPersonId(personId);
    }

    // Update
    @RequestMapping(path="/person", method=RequestMethod.PUT)
    public void updatePerson(@RequestBody Person item) {

        Person person = mongoPersonRepo.findByPersonId(item.getPersonId());
        person.setDescription(item.getDescription());
        person.setName(item.getName());
        person.setIsDeceased(item.getIsDeceased());
        mongoPersonRepo.save(person);
    }

    // Delete
    @RequestMapping(path="/person/{id}", method=RequestMethod.DELETE)
    public void deleteEntry(@PathVariable String id) {
        int personId;
        try {
            personId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return;
        }
        mongoPersonRepo.deleteByPersonId(personId);
    }

    /* Devil */

    // Create
    @RequestMapping(path="/devil", method= RequestMethod.POST)
    public void CreateDevil(@RequestBody Devil item) {
        List<Devil> devils = mongoDevilRepo.findAll();
        for (Devil devil : devils) {
            if (devil.getDevilId() == item.getDevilId()) {
                return;
            }
        }
        mongoDevilRepo.save(item);
    }

    // Read
    @RequestMapping(path="/devil", method=RequestMethod.GET)
    public List<Devil> findAllDevils() {
        return mongoDevilRepo.findAll();
    }

    @RequestMapping(path="/devil/{id}", method=RequestMethod.GET)
    public Devil findDevilById(@PathVariable String id) {
        int devilId;
        try {
            devilId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return mongoDevilRepo.findByDevilId(devilId);
    }

    // Update
    @RequestMapping(path="/devil", method=RequestMethod.PUT)
    public void updateDevil(@RequestBody Devil item) {

        Devil devil = mongoDevilRepo.findByDevilId(item.getDevilId());
        devil.setDescription(item.getDescription());
        devil.setName(item.getName());
        devil.setType(item.getType());
        mongoDevilRepo.save(devil);
    }

    // Delete
    @RequestMapping(path="/devil/{id}", method=RequestMethod.DELETE)
    public void deleteDevil(@PathVariable String id) {
        int devilId;
        try {
            devilId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return;
        }
        mongoDevilRepo.deleteByDevilId(devilId);
    }

    /* Location */

    // Create
    @RequestMapping(path="/location", method= RequestMethod.POST)
    public void CreateLocation(@RequestBody Location item) {
        List<Location> locations = mongoLocationRepo.findAll();
        for (Location location : locations) {
            if (location.getLocationId() == item.getLocationId()) {
                return;
            }
        }
        mongoLocationRepo.save(item);
    }

    // Read
    @RequestMapping(path="/location", method=RequestMethod.GET)
    public List<Location> findAllLocations() {
        return mongoLocationRepo.findAll();
    }

    @RequestMapping(path="/location/{id}", method=RequestMethod.GET)
    public Location findLocationById(@PathVariable String id) {
        int locationId;
        try {
            locationId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return mongoLocationRepo.findByLocationId(locationId);
    }

    // Update
    @RequestMapping(path="/location", method=RequestMethod.PUT)
    public void updateLocation(@RequestBody Location item) {

        Location location = mongoLocationRepo.findByLocationId(item.getLocationId());
        location.setDescription(item.getDescription());
        location.setName(item.getName());
        mongoLocationRepo.save(location);
    }

    // Delete
    @RequestMapping(path="/location/{id}", method=RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String id) {
        int locationId;
        try {
            locationId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return;
        }
        mongoLocationRepo.deleteByLocationId(locationId);
    }

}
