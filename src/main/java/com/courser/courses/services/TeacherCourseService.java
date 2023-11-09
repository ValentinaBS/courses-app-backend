package com.courser.courses.services;

import com.courser.courses.dtos.TeacherCourseDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.subclass.Teacher;
import com.courser.courses.models.TeacherCourse;

import java.util.Set;

public interface TeacherCourseService {

    Set<TeacherCourseDTO> getTeacherCourseDTO();
    TeacherCourse findById(long id);
    boolean existsByTeacherAndCourse(Teacher teacher, Course course);
    void saveTeacherCourse(TeacherCourse teacherCourse);
}
