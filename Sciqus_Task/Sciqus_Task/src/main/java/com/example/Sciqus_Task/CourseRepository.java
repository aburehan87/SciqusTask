package com.example.Sciqus_Task;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Custom method to find a course by its name
    Optional<Course> findByCourseName(String courseName);
}
