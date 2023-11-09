package com.courser.courses.services.implement;


import com.courser.courses.dtos.TeacherCourseDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.subclass.Teacher;
import com.courser.courses.models.TeacherCourse;
import com.courser.courses.repositories.TeacherCourseRepository;
import com.courser.courses.services.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class TeacherCourseServiceImplement implements TeacherCourseService {
    @Autowired
    TeacherCourseRepository teacherCourseRepository;

    @Override
    public Set<TeacherCourseDTO> getTeacherCourseDTO() {
        return teacherCourseRepository
                .findAll()
                .stream()
                .map(TeacherCourseDTO::new)
                .collect(toSet());
    }

    @Override
    public TeacherCourse findById(long id) {
        return teacherCourseRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByTeacherAndCourse(Teacher teacher, Course course) {
        return teacherCourseRepository.existsByTeacherAndCourse(teacher, course);
    }

    @Override
    public void saveTeacherCourse(TeacherCourse teacherCourse) {
        teacherCourseRepository.save(teacherCourse);
    }
}
