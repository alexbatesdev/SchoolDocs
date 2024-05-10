package com.example.fantasyapi.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "People")
public class Person {
    @Id
    private ObjectId _id;
    private int personId;
    private String name;
    private boolean isDeceased;
    private String description;

    public Person(int personId, String name, boolean isDeceased, String description) {
        this.personId = personId;
        this.name = name;
        this.isDeceased = isDeceased;
        this.description = description;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDeceased() {
        return isDeceased;
    }

    public void setIsDeceased(boolean isDeceased) {
        this.isDeceased = isDeceased;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", name='" + name + '\'' +
                ", data='" + description + '\'' +
                '}';
    }

}
