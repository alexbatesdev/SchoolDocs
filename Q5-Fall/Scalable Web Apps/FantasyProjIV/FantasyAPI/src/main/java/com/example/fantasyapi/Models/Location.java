package com.example.fantasyapi.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Locations")
public class Location {
    @Id
    private ObjectId _id;
    private int locationId;
    private String name;
    private String description;

    public Location(int locationId, String name, String description) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + locationId +
                ", name='" + name + '\'' +
                ", data='" + description + '\'' +
                '}';
    }
}
