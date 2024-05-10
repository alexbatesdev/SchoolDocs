package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepo extends MongoRepository<User, String> {

        User findByUserId(int userId);

        void deleteByUserId(int userId);

}
