package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonJpaRepo extends JpaRepository<Person, String> {

    Person findByPersonId(Long personId);

    void deleteByPersonId(Long personId);

    List<Person> findByNameLike(String s);
}
