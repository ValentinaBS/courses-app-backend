package com.courser.courses.services.implement;

import com.courser.courses.dtos.CourseDTO;
import com.courser.courses.models.Course;
import com.courser.courses.repositories.CourseRepository;
import com.courser.courses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class CourseServiceImplement implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }

    @Override
    public Set<CourseDTO> getCoursesDTO() {
        return courseRepository
                .findAll()
                .stream()
                .map(CourseDTO::new)
                .collect(toSet());
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
