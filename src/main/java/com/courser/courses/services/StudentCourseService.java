package com.courser.courses.services;

import com.courser.courses.models.*;

public interface StudentCourseService {
    StudentCourse findById(long id);
    StudentCourse findByStudentAndCourse(Student student, Course course);
    boolean existsByStudentAndCourse(Student student, Course course);
    void saveStudentCourse(StudentCourse studentCourse);
}
