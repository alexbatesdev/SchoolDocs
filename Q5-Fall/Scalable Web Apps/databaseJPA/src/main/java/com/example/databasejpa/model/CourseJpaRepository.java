package com.example.databasejpa.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, Integer> {
    
    Course findByCourseCode(String courseCode);

    void deleteByCourseCode(String courseCode);
}
