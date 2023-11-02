package com.courser.courses.services;

import com.courser.courses.models.Course;
import com.courser.courses.dtos.CourseDTO;

import java.util.Set;

public interface CourseService {
    Course findById(Long id);
    Course findByName(String name);
    boolean existsByName(String name);

    Set<CourseDTO> getCoursesDTO();

    void saveCourse(Course course);
}
