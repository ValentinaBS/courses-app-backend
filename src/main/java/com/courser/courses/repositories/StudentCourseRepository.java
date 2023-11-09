package com.courser.courses.repositories;

import com.courser.courses.models.Course;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    StudentCourse findByStudentAndCourse(Student student, Course course);
    boolean existsByStudentAndCourse(Student student, Course course);
}
