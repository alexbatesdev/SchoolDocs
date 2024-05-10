package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.Models.Location;
import com.example.fantasyapi.Onion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class SleepyLocationController {
    // Create
    @RequestMapping(path="", method= RequestMethod.POST)
    public void CreateEntry(@RequestBody Location item) {
        Onion.saveLocation(item);
    }

    // Read
    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Location> findAllEntries() {
        return Onion.getLocations();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Location findEntryById(@PathVariable int id) {
        return Onion.getLocation(id);
    }

    // Update
    @RequestMapping(path="", method=RequestMethod.PUT)
    public void updateEntry(@RequestBody Location item) {
        Onion.updateLocation(item);
    }

    // Delete
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteEntry(@PathVariable int id) {
        Onion.deleteLocation(id);
    }
}
