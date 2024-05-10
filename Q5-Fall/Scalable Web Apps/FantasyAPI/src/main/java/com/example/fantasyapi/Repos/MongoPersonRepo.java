package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoPersonRepo extends MongoRepository<Person, String> {

    Person findByPersonId(int personId);

    void deleteByPersonId(int personId);
}
