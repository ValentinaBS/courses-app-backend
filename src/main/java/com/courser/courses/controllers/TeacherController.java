package com.courser.courses.controllers;

import com.courser.courses.dtos.TeacherDTO;
import com.courser.courses.models.enums.Role;
import com.courser.courses.models.subclass.Teacher;
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
            @RequestParam String fullName, @RequestParam String email, @RequestParam String password, @RequestParam String major) {

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Please don't leave any empty fields.", HttpStatus.FORBIDDEN);
        }
        if (teacherService.existsByEmail(email)) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.FORBIDDEN);
        }

        teacherService.saveTeacher(new Teacher(fullName, email, passwordEncoder.encode(password), Role.TEACHER, true, major));
        return new ResponseEntity<>("Teacher has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Object> updateTeacher(
            @RequestBody Teacher newTeacher,
            @PathVariable Long id) {

        Teacher oldTeacher = teacherService.findById(id);
        if (oldTeacher == null) {
            return new ResponseEntity<>("Couldn't find a teacher to update with that id.", HttpStatus.FORBIDDEN);
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

    @PatchMapping("/teachers/{id}")
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

        return new ResponseEntity<>("Teacher removed successfully", HttpStatus.OK);
    }
}
