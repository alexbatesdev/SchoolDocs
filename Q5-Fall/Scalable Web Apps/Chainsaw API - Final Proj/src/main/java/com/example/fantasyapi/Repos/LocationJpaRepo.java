package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationJpaRepo extends JpaRepository<Location, String> {

        Location findByLocationId(Long locationId);

        void deleteByLocationId(Long locationId);
}

