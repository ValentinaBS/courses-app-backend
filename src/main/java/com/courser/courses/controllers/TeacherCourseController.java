package com.courser.courses.controllers;

import com.courser.courses.dtos.TeacherCourseDTO;
import com.courser.courses.dtos.TeacherDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.Role;
import com.courser.courses.models.Teacher;
import com.courser.courses.models.TeacherCourse;
import com.courser.courses.repositories.TeacherCourseRepository;
import com.courser.courses.services.CourseService;
import com.courser.courses.services.TeacherCourseService;
import com.courser.courses.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TeacherCourseController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("/teacherCourse")
    public Set<TeacherCourseDTO> getTeacherCourses() {
        return teacherCourseService.getTeacherCourseDTO();
    }

    @PostMapping("/teacherCourse")
    public ResponseEntity<Object> createTeacherCourse(@RequestParam Long teacherId, @RequestParam Long courseId) {

        Course course = courseService.findById(courseId);
        if(course == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        Teacher teacher = teacherService.findById(teacherId);
        if(teacher == null) {
            return new ResponseEntity<>("This teacher doesn't exist.", HttpStatus.FORBIDDEN);
        }

        TeacherCourse teacherCourse = new TeacherCourse(false);
        teacherCourse.setTeacher(teacher);
        teacherCourse.setCourse(course);
        teacherCourseService.saveTeacherCourse(teacherCourse);

        return new ResponseEntity<>("The teacher has been added to the course successfully", HttpStatus.OK);
    }

    @PatchMapping("/teacherCourse/{id}")
    public ResponseEntity<Object> removeTeacherCourse(@PathVariable Long id) {

        TeacherCourse teacherCourse = teacherCourseService.findById(id);
        if(teacherCourse == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if (teacherCourse.getTeacher() == null) {
            return new ResponseEntity<>("This course doesn't have a teacher.", HttpStatus.FORBIDDEN);
        }
        Teacher defaultTeacher = new Teacher("No Teacher", "teacher@email.com", "defaultTeacher123", Role.TEACHER, false);
        teacherService.saveTeacher(defaultTeacher);

        teacherCourse.setTeacher(defaultTeacher);
        teacherCourseService.saveTeacherCourse(teacherCourse);

        return new ResponseEntity<>("Teacher has been removed successfully from the course", HttpStatus.OK);
    }
}
