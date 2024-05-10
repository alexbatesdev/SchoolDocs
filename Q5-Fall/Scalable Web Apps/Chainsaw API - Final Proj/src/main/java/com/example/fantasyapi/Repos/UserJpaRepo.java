package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, String> {

        User findByUserId(Long userId);

        void deleteByUserId(Long userId);

}
