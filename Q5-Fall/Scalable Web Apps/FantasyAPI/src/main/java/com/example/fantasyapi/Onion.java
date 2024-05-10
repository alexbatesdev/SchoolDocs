package com.example.fantasyapi;

import com.example.fantasyapi.Models.Devil;
import com.example.fantasyapi.Models.JSONModel;
import com.example.fantasyapi.Models.Location;
import com.example.fantasyapi.Models.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Onion {
    private static File file = new File("data.json");

    public static JSONModel getJSON() {
        // if there isn't a file, create one
        if (!file.exists()) {
            return new JSONModel();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        JSONModel json = null;

        // read the file
        try {
            json = mapper.readValue(file, new TypeReference<JSONModel>() {});
        } catch (Exception e) {
            System.out.println("Oops: " + e.getMessage());
        }
        return json;
    }



    public static List<Person> getPeople() {
        JSONModel json = getJSON();
        //GET PERSON LIST FROM JSONModel
        ArrayList<Person> people = json.people;
        return people;
    }

    public static Person getPerson(int id) {
        JSONModel json = getJSON();
        //GET PERSON LIST FROM JSONModel
        ArrayList<Person> people = json.people;
        for (Person person : people) {
            // returns the first person with the matching id
            if (person.getPersonId() == id) {
                return person;
            }
        }
        return null;
    }

    public static void savePerson(Person victim) {
        JSONModel model = getJSON();

        // Before creating a new entry it checks to see if the id already exists and if it does, it updates the entry
        boolean found = false;
        for (int i = 0; i < model.people.size(); i++) {
            if (model.people.get(i).getPersonId() == victim.getPersonId()) {
                model.people.set(i, victim);
                found = true;
            }
        }
        // If the id doesn't exist it creates a new entry
        if (!found) {
            model.people.add(victim);
        }
        //This is how I deal with duplicates

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void updatePerson(Person victim) {
        JSONModel model = getJSON();

        // Finds the entry with the matching id and updates it
        for (int i = 0; i < model.people.size(); i++) {
            if (model.people.get(i).getPersonId() == victim.getPersonId()) {
                model.people.set(i, victim);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void deletePerson(int id) {
        JSONModel model = getJSON();

        // Finds the entry with the matching id and deletes it
        for (int i = 0; i < model.people.size(); i++) {
            if (model.people.get(i).getPersonId() == id) {
                model.people.remove(i);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }



    public static List<Devil> getDevils() {
        JSONModel json = getJSON();
        //GET DEVIL LIST FROM JSONModel
        ArrayList<Devil> devils = json.devils;
        return devils;
    }

    public static Devil getDevil(int id) {
        JSONModel json = getJSON();
        //GET DEVIL LIST FROM JSONModel
        ArrayList<Devil> devils = json.devils;
        for (Devil devil : devils) {
            if (devil.getDevilId() == id) {
                return devil;
            }
        }
        return null;
    }

    public static void saveDevil(Devil devil) {
        JSONModel model = getJSON();

        boolean found = false;
        for (int i = 0; i < model.devils.size(); i++) {
            if (model.devils.get(i).getDevilId() == devil.getDevilId()) {
                model.devils.set(i, devil);
                found = true;
            }
        }
        if (!found) {
            model.devils.add(devil);
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void updateDevil(Devil devil) {
        JSONModel model = getJSON();
        for (int i = 0; i < model.devils.size(); i++) {
            if (model.devils.get(i).getDevilId() == devil.getDevilId()) {
                model.devils.set(i, devil);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void deleteDevil(int id) {
        JSONModel model = getJSON();
        for (int i = 0; i < model.devils.size(); i++) {
            if (model.devils.get(i).getDevilId() == id) {
                model.devils.remove(i);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }



    public static List<Location> getLocations() {
        JSONModel json = getJSON();
        //GET LOCATION LIST FROM JSONModel
        ArrayList<Location> locations = json.locations;
        return locations;
    }

    public static Location getLocation(int id) {
        JSONModel json = getJSON();
        //GET LOCATION LIST FROM JSONModel
        ArrayList<Location> locations = json.locations;
        for (Location location : locations) {
            if (location.getLocationId() == id) {
                return location;
            }
        }
        return null;
    }

    public static void saveLocation(Location place) {
        JSONModel model = getJSON();

        boolean found = false;
        for (int i = 0; i < model.locations.size(); i++) {
            if (model.locations.get(i).getLocationId() == place.getLocationId()) {
                model.locations.set(i, place);
                found = true;
            }
        }
        if (!found) {
            model.locations.add(place);
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void updateLocation(Location place) {
        JSONModel model = getJSON();
        for (int i = 0; i < model.locations.size(); i++) {
            if (model.locations.get(i).getLocationId() == place.getLocationId()) {
                model.locations.set(i, place);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }

    public static void deleteLocation(int id) {
        JSONModel model = getJSON();
        for (int i = 0; i < model.locations.size(); i++) {
            if (model.locations.get(i).getLocationId() == id) {
                model.locations.remove(i);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        //used for less common data types
        mapper.findAndRegisterModules();

        //make pretty
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, model);
        } catch (IOException e) {
            System.out.println("oops: " + e.getMessage());
        }

    }
}
