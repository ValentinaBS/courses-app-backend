package com.courser.courses.services.implement;

import com.courser.courses.dtos.StudentCourseDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.StudentCourse;
import com.courser.courses.repositories.StudentCourseRepository;
import com.courser.courses.services.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class StudentCourseServiceImplement implements StudentCourseService {
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Override
    public StudentCourse findById(long id) {
        return studentCourseRepository.findById(id).orElse(null);
    }

    @Override
    public StudentCourse findByStudentAndCourse(Student student, Course course) {
        return studentCourseRepository.findByStudentAndCourse(student, course);
    }

    @Override
    public Set<StudentCourseDTO> getStudentCourseDTO() {
        return studentCourseRepository
                .findAll()
                .stream()
                .map(StudentCourseDTO::new)
                .collect(toSet());
    }

    @Override
    public boolean existsByStudentAndCourse(Student student, Course course) {
        return studentCourseRepository.existsByStudentAndCourse(student, course);
    }

    @Override
    public void saveStudentCourse(StudentCourse studentCourse) {
        studentCourseRepository.save(studentCourse);
    }
}
