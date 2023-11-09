package com.courser.courses.controllers;

import com.courser.courses.dtos.StudentCourseDTO;
import com.courser.courses.models.*;
import com.courser.courses.models.enums.Role;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentCourseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/studentCourse")
    public Set<StudentCourseDTO> getStudentCourses() {
        return studentCourseService.getStudentCourseDTO();
    }

    @PostMapping("/studentCourse")
    public ResponseEntity<Object> createStudentCourse(@RequestParam Long studentId, @RequestParam Long courseId) {

        Course course = courseService.findById(courseId);
        if(course == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        Student student = studentService.findById(studentId);
        if(student == null) {
            return new ResponseEntity<>("This student doesn't exist.", HttpStatus.FORBIDDEN);
        }

        StudentCourse studentCourse = new StudentCourse(false);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourseService.saveStudentCourse(studentCourse);

        return new ResponseEntity<>("The student has been added to the course successfully", HttpStatus.OK);
    }

    @PatchMapping("/studentCourse/{id}")
    public ResponseEntity<Object> removeStudentCourse(@PathVariable Long id) {

        StudentCourse studentCourse = studentCourseService.findById(id);
        if(studentCourse == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if (studentCourse.getStudent() == null) {
            return new ResponseEntity<>("This course doesn't have a student.", HttpStatus.FORBIDDEN);
        }
        Student defaultStudent = new Student("No Students", "student@email.com", "defaultStudent123", Role.STUDENT, false, "-");
        studentService.saveStudent(defaultStudent);

        studentCourse.setStudent(defaultStudent);
        studentCourseService.saveStudentCourse(studentCourse);

        return new ResponseEntity<>("Student has been removed successfully from the course", HttpStatus.OK);
    }
}
