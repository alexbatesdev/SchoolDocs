package com.example.fantasyapi.Controllers;

import com.example.fantasyapi.Models.Devil;
import com.example.fantasyapi.Onion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devil")
public class SleepyDevilController {
    // Create
    @RequestMapping(path="", method= RequestMethod.POST)
    public void CreateEntry(@RequestBody Devil item) {
        Onion.saveDevil(item);
    }

    // Read
    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Devil> findAllEntries() {
        return Onion.getDevils();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Devil findEntryById(@PathVariable int id) {
        return Onion.getDevil(id);
    }

    // Update
    @RequestMapping(path="", method=RequestMethod.PUT)
    public void updateEntry(@RequestBody Devil item) {
        Onion.updateDevil(item);
    }

    // Delete
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteEntry(@PathVariable int id) {
        Onion.deleteDevil(id);
    }
}
