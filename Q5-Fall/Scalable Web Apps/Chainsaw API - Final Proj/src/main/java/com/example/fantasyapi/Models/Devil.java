package com.example.fantasyapi.Models;



import javax.persistence.*;
import java.util.List;

@Entity
public class Devil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long devilId;
    private String name;
    private String description;
    private String type;

    @OneToMany
    private List<Contract> contracts;

    public Devil(Long devilId, String name, String description, String type) {
        this.devilId = devilId;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Devil() {

    }

    public Long getDevilId() {
        return devilId;
    }

    public void setDevilId(Long devilId) {
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
