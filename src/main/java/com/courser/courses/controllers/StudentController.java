package com.courser.courses.controllers;

import com.courser.courses.dtos.StudentDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.enums.Role;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.StudentCourse;
import com.courser.courses.models.supclass.Person;
import com.courser.courses.repositories.PersonRepository;
import com.courser.courses.services.CourseService;
import com.courser.courses.services.StudentCourseService;
import com.courser.courses.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/students")
    public Set<StudentDTO> getStudents() {
        return studentService.getStudentsDTO();
    }

    @GetMapping("/students/current")
    public StudentDTO getStudentCurrent(Authentication authentication) {
        return studentService.getCurrentStudent(authentication.getName());
    }

    @GetMapping("/students/search")
    public ResponseEntity<Object> getStudentByNameAndCourse(@RequestParam String fullName, @RequestParam String courseName){

        Course course = courseService.findByName(courseName);
        if (course == null) {
            return new ResponseEntity<>("Couldn't find the course.", HttpStatus.FORBIDDEN);
        }
        Student student = studentService.findByFullName(fullName);
        if (student == null) {
            return new ResponseEntity<>("Couldn't find the student.", HttpStatus.FORBIDDEN);
        }
        StudentCourse studentCourse = studentCourseService.findByStudentAndCourse(student, course);
        if(studentCourse == null) {
            return new ResponseEntity<>("Couldn't find a student with that name and course.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> registerStudent(
            @RequestParam String fullName, @RequestParam String email, @RequestParam String password, @RequestParam String description) {

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Please don't leave any empty fields.", HttpStatus.FORBIDDEN);
        }
        if (studentService.existsByEmail(email)) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.FORBIDDEN);
        }

        studentService.saveStudent(new Student(fullName, email, passwordEncoder.encode(password), Role.STUDENT, true, description));
        return new ResponseEntity<>("Student has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(
            @RequestBody Student newStudent,
            @PathVariable Long id) {

        Student oldStudent = studentService.findById(id);
        if (oldStudent == null) {
            return new ResponseEntity<>("Couldn't find a student to update with that id.", HttpStatus.FORBIDDEN);
        }
        if (newStudent.getRole() != Role.STUDENT) {
            return new ResponseEntity<>("This user is not a student.", HttpStatus.FORBIDDEN);
        }

        oldStudent.setFullName(newStudent.getFullName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setPassword(newStudent.getPassword());
        oldStudent.setActive(newStudent.getActive());

        studentService.saveStudent(oldStudent);
        return ResponseEntity.ok("Updated student data");
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Object> removeStudent(@PathVariable Long id) {

        Student student = studentService.findById(id);
        if(student == null) {
            return new ResponseEntity<>("This student doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if(!student.getActive()){
            return new ResponseEntity<>("This student is already removed", HttpStatus.FORBIDDEN);
        }

        student.setActive(false);
        studentService.saveStudent(student);

        return new ResponseEntity<>("Student removed successfully", HttpStatus.OK);
    }
}
