package com.example.fantasyapi.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;
    private String name;
    private String description;

    public Location(Long locationId, String name, String description) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
    }

    public Location() {

    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
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
