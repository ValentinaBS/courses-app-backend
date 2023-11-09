package com.courser.courses.services;

import com.courser.courses.dtos.StudentCourseDTO;
import com.courser.courses.models.*;
import com.courser.courses.models.subclass.Student;

import java.util.Set;

public interface StudentCourseService {
    StudentCourse findById(long id);
    StudentCourse findByStudentAndCourse(Student student, Course course);
    Set<StudentCourseDTO> getStudentCourseDTO();
    boolean existsByStudentAndCourse(Student student, Course course);
    void saveStudentCourse(StudentCourse studentCourse);
}
