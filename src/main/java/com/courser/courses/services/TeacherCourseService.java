package com.courser.courses.services;

import com.courser.courses.models.Course;
import com.courser.courses.models.Teacher;
import com.courser.courses.models.TeacherCourse;

public interface TeacherCourseService {
    TeacherCourse findById(long id);
    boolean existsByTeacherAndCourse(Teacher teacher, Course course);
    void saveTeacherCourse(TeacherCourse teacherCourse);
}
