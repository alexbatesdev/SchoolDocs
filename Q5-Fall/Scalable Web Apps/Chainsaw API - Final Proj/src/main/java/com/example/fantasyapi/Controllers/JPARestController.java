package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.Models.*;
import com.example.fantasyapi.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/JPA")
public class JPARestController {

    @Autowired
    UserJpaRepo jpaUserRepo;

    @Autowired
    PersonJpaRepo jpaPersonRepo;

    @Autowired
    DevilJpaRepo jpaDevilRepo;

    @Autowired
    LocationJpaRepo jpaLocationRepo;

    @Autowired
    ContractJpaRepo jpaContractRepo;

    // Login
    @RequestMapping(path="/login", method= RequestMethod.GET)
    public void login() {

    }

    @RequestMapping(path="/user", method= RequestMethod.POST)
    public void user(@RequestBody User user) {
        List<User> users = jpaUserRepo.findAll();
        Long largestId = 0L;
        for (User u : users) {
            if (u.getUserId() > largestId) {
                largestId = u.getUserId();
            }
            if (u.getUsername().equals(user.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        user.userId = largestId + 1;
        jpaUserRepo.save(user);
    }

    /* Person */
    // Create
    @RequestMapping(path="/person", method= RequestMethod.POST)
    public void CreatePerson(@RequestBody Person item) {
        List<Person> people = jpaPersonRepo.findAll();
        for (Person person : people) {
            if (person.getPersonId() == item.getPersonId()) {
                return;
            }
        }
        jpaPersonRepo.save(item);
    }

    // Read
    @RequestMapping(path="/person", method=RequestMethod.GET)
    public List<Person> findAllPeople() {
        System.out.println("findAllPeople");
        System.out.println(jpaPersonRepo.findAll());
        return jpaPersonRepo.findAll();
    }

    @RequestMapping(path="/person/{id}", method=RequestMethod.GET)
    public Person findPersonById(@PathVariable String id) {
        Long personId;
        try {
            personId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return jpaPersonRepo.findByPersonId(personId);
    }

    // Update
    @RequestMapping(path="/person", method=RequestMethod.PUT)
    public void updatePerson(@RequestBody Person item) {

        Person person = jpaPersonRepo.findByPersonId(item.getPersonId());
        person.setDescription(item.getDescription());
        person.setName(item.getName());
        person.setIsDeceased(item.getIsDeceased());
        jpaPersonRepo.save(person);
    }

    // Delete
    @RequestMapping(path="/person/{id}", method=RequestMethod.DELETE)
    public void deleteEntry(@PathVariable String id) {
        Long personId;
        try {
            personId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return;
        }
        jpaPersonRepo.deleteByPersonId(personId);
    }

    /* Devil */

    // Create
    @RequestMapping(path="/devil", method= RequestMethod.POST)
    public void CreateDevil(@RequestBody Devil item) {
        List<Devil> devils = jpaDevilRepo.findAll();
        for (Devil devil : devils) {
            if (devil.getDevilId() == item.getDevilId()) {
                return;
            }
        }
        jpaDevilRepo.save(item);
    }

    // Read
    @RequestMapping(path="/devil", method=RequestMethod.GET)
    public List<Devil> findAllDevils() {
        return jpaDevilRepo.findAll();
    }

    @RequestMapping(path="/devil/{id}", method=RequestMethod.GET)
    public Devil findDevilById(@PathVariable String id) {
        Long devilId;
        try {
            devilId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return jpaDevilRepo.findByDevilId(devilId);
    }

    // Update
    @RequestMapping(path="/devil", method=RequestMethod.PUT)
    public void updateDevil(@RequestBody Devil item) {

        Devil devil = jpaDevilRepo.findByDevilId(item.getDevilId());
        devil.setDescription(item.getDescription());
        devil.setName(item.getName());
        devil.setType(item.getType());
        jpaDevilRepo.save(devil);
    }

    // Delete
    @RequestMapping(path="/devil/{id}", method=RequestMethod.DELETE)
    public void deleteDevil(@PathVariable String id) {
        Long devilId;
        try {
            devilId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return;
        }
        jpaDevilRepo.deleteByDevilId(devilId);
    }

    /* Location */

    // Create
    @RequestMapping(path="/location", method= RequestMethod.POST)
    public void CreateLocation(@RequestBody Location item) {
        List<Location> locations = jpaLocationRepo.findAll();
        for (Location location : locations) {
            if (location.getLocationId() == item.getLocationId()) {
                return;
            }
        }
        jpaLocationRepo.save(item);
    }

    // Read
    @RequestMapping(path="/location", method=RequestMethod.GET)
    public List<Location> findAllLocations() {
        return jpaLocationRepo.findAll();
    }

    @RequestMapping(path="/location/{id}", method=RequestMethod.GET)
    public Location findLocationById(@PathVariable String id) {
        Long locationId;
        try {
            locationId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return jpaLocationRepo.findByLocationId(locationId);
    }

    // Update
    @RequestMapping(path="/location", method=RequestMethod.PUT)
    public void updateLocation(@RequestBody Location item) {

        Location location = jpaLocationRepo.findByLocationId(item.getLocationId());
        location.setDescription(item.getDescription());
        location.setName(item.getName());
        jpaLocationRepo.save(location);
    }

    // Delete
    @RequestMapping(path="/location/{id}", method=RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String id) {
        Long locationId;
        try {
            locationId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return;
        }
        jpaLocationRepo.deleteByLocationId(locationId);
    }

    /* Contracts */

    // Create
    @RequestMapping(path="/contract/{person}/with/{devil}", method= RequestMethod.POST)
    public void CreateContract(@PathVariable String person, @PathVariable String devil) {
        Long personId;
        Long devilId;
        try {
            personId = Long.parseLong(person);
            devilId = Long.parseLong(devil);
        } catch (NumberFormatException e) {
            return;
        }
        Contract contract = new Contract();
        contract.setPersonId(personId);
        contract.setDevilId(devilId);
        jpaContractRepo.save(contract);
    }

    // Read
    @RequestMapping(path="/contract", method=RequestMethod.GET)
    public List<Contract> findAllContracts() {
        return jpaContractRepo.findAll();
    }

    @RequestMapping(path="/contract/{id}", method=RequestMethod.GET)
    public Contract findContractById(@PathVariable String id) {
        Long contractId;
        try {
            contractId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return jpaContractRepo.findByContractId(contractId);
    }

    // Update
    @RequestMapping(path="/contract/{contractId}/{person}/with/{devil}", method=RequestMethod.PUT)
    public void updateContract(@PathVariable String contractId, @PathVariable String person, @PathVariable String devil) {
        Contract contract = jpaContractRepo.findByContractId(Long.parseLong(contractId));
        contract.setDevilId(Long.parseLong(devil));
        contract.setPersonId(Long.parseLong(person));
        jpaContractRepo.save(contract);
    }

    // Delete
    @RequestMapping(path="/contract/{id}", method=RequestMethod.DELETE)
    public void deleteContract(@PathVariable String id) {
        Long contractId;
        try {
            contractId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return;
        }
        jpaContractRepo.deleteByContractId(contractId);
    }

    /* Search */
    @RequestMapping(path="/search/{name}", method=RequestMethod.GET)
    public List<Object> searchByName(@PathVariable String name) {
        // JPA function method
        List<Person> people = jpaPersonRepo.findByNameLike("%" + name + "%");
        // JPA query method
        List<Devil> devils = jpaDevilRepo.queryByNameLike("%" + name + "%");
        // Manual method
        List<Location> locations = new ArrayList<>();
        for (Location location : jpaLocationRepo.findAll()) {
            if (location.getName().toLowerCase().contains(name.toLowerCase())) {
                locations.add(location);
            }
        }

        List<Object> results = new ArrayList<>();
        results.addAll(people);
        results.addAll(devils);
        results.addAll(locations);
        return results;
    }

}
