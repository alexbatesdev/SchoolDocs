package com.example.databasejpa.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {


    List<Student> findByNameLike(String s);

    List<Student> findByGpaGreaterThan(double gpa);

    List<Student> findByNameLikeAndGpaGreaterThanEqual(String s, double gpa);
    //@Query(value="SELECT * FROM student", nativeQuery=true) //native SQL query
    @Query("select s from Student s where s.name Like:t1") //JPA Query
    List<Student> queryByNameLike(@Param("t1") String text);
}
