package com.courser.courses.repositories;

import com.courser.courses.models.Course;
import com.courser.courses.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
    boolean existsByName(String name);
}
