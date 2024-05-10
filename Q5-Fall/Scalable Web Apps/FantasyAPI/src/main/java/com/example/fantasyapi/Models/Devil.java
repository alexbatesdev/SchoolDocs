package com.example.fantasyapi.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Devils")
public class Devil {
    @Id
    private ObjectId _id;
    private int devilId;
    private String name;
    private String description;
    private String type;

    public Devil(int devilId, String name, String description, String type) {
        this.devilId = devilId;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getDevilId() {
        return devilId;
    }

    public void setDevilId(int devilId) {
        this.devilId = devilId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Devil{" +
                "id=" + devilId +
                ", name='" + name + '\'' +
                ", data='" + description + '\'' +
                '}';
    }
}
