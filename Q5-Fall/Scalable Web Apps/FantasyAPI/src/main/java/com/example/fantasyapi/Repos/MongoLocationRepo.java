package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoLocationRepo extends MongoRepository<Location, String> {

        Location findByLocationId(int locationId);

        void deleteByLocationId(int locationId);
}

