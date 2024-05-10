package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Devil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevilJpaRepo extends JpaRepository<Devil, String> {
    Devil findByDevilId(Long devilId);

    void deleteByDevilId(Long devilId);

    @Query(value = "SELECT * FROM devil WHERE name LIKE:p1", nativeQuery = true)
    List<Devil> queryByNameLike(@Param("p1") String name);
}
