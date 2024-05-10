package com.example.fantasyapi.Models;


import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    private String name;
    private boolean isDeceased;
    private String description;

    @ManyToOne
    private Contract contract;

    public Person(Long personId, String name, boolean isDeceased, String description) {
        this.personId = personId;
        this.name = name;
        this.isDeceased = isDeceased;
        this.description = description;
    }

    public Person() {

    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
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
