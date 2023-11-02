package com.courser.courses.dtos;

import com.courser.courses.models.Role;
import com.courser.courses.models.Student;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentDTO {
    private long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Boolean active;
    private Set<StudentCourseDTO> studentCourses;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.fullName = student.getFullName();
        this.email = student.getEmail();
        this.password = student.getPassword();
        this.role = student.getRole();
        this.active = student.getActive();
        this.studentCourses = student.getStudentCourses().stream().map(StudentCourseDTO::new).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getActive() {
        return active;
    }

    public Set<StudentCourseDTO> getStudentCourses() {
        return studentCourses;
    }
}
