package com.courser.courses.controllers;

import com.courser.courses.dtos.TeacherDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.Role;
import com.courser.courses.models.Teacher;
import com.courser.courses.services.CourseService;
import com.courser.courses.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/teachers")
    public Set<TeacherDTO> getTeachers() {
        return teacherService.getTeachersDTO();
    }

    @GetMapping("/teachers/current")
    public TeacherDTO getTeacherCurrent(Authentication authentication) {
        return teacherService.getCurrentTeacher(authentication.getName());
    }

    @PostMapping("/teachers")
    public ResponseEntity<Object> registerTeacher(
            @RequestParam String fullName, @RequestParam String email, @RequestParam String password) {

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Please don't leave any empty fields.", HttpStatus.FORBIDDEN);
        }
        if (teacherService.existsByEmail(email)) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.FORBIDDEN);
        }

        teacherService.saveTeacher(new Teacher(fullName, email, passwordEncoder.encode(password), Role.TEACHER, true));
        return new ResponseEntity<>("Teacher has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Object> updateTeacher(
            @RequestBody Teacher newTeacher,
            @PathVariable Long id) {

        Teacher oldTeacher = teacherService.findById(id);
        if (oldTeacher == null) {
            return new ResponseEntity<>("Couldn't find a student to update with that id.", HttpStatus.FORBIDDEN);
        }
        if (newTeacher.getRole() != Role.TEACHER) {
            return new ResponseEntity<>("This user is not a teacher.", HttpStatus.FORBIDDEN);
        }

        oldTeacher.setFullName(newTeacher.getFullName());
        oldTeacher.setEmail(newTeacher.getEmail());
        oldTeacher.setPassword(newTeacher.getPassword());
        oldTeacher.setActive(newTeacher.getActive());

        teacherService.saveTeacher(oldTeacher);
        return ResponseEntity.ok("Updated teacher data");
    }

    @PatchMapping("/teachers/remove/{id}")
    public ResponseEntity<Object> removeTeacher(@PathVariable Long id) {

        Teacher teacher = teacherService.findById(id);
        if(teacher == null) {
            return new ResponseEntity<>("This teacher doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if(!teacher.getActive()){
            return new ResponseEntity<>("This teacher is already removed", HttpStatus.FORBIDDEN);
        }

        teacher.setActive(false);
        teacherService.saveTeacher(teacher);

        return new ResponseEntity<>("Student removed successfully", HttpStatus.OK);
    }

    @PatchMapping("/teachers/removeCourse/{id}")
    public ResponseEntity<Object> removeTeacherCourse(@PathVariable Long id, @RequestParam Long courseId) {

        Course course = courseService.findById(id);
        if(course == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        Teacher teacher = teacherService.findById(id);
        if(teacher == null) {
            return new ResponseEntity<>("This teacher doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if(course.getTeacherCourses() == null){
            return new ResponseEntity<>("This course doesn't have a teacher.", HttpStatus.FORBIDDEN);
        }
        if(teacher.getTeacherCourses() == null){
            return new ResponseEntity<>("This teacher doesn't have a course.", HttpStatus.FORBIDDEN);
        }

        course.setTeacherCourses(null);
        teacher.setTeacherCourses(null);
        courseService.saveCourse(course);
        teacherService.saveTeacher(teacher);

        return new ResponseEntity<>("Teacher has been removed successfully from the course", HttpStatus.OK);
    }
}
