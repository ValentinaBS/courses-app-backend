package com.courser.courses.repositories;

import com.courser.courses.models.*;
import com.courser.courses.models.subclass.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {
    boolean existsByTeacherAndCourse(Teacher teacher, Course course);
}
