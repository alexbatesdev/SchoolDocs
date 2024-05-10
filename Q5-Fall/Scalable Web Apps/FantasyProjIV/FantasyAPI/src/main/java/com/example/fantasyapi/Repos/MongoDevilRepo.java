package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Devil;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDevilRepo extends MongoRepository<Devil, String> {
    Devil findByDevilId(int devilId);

    void deleteByDevilId(int devilId);
}
