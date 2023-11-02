package com.courser.courses.dtos;

import com.courser.courses.models.Role;
import com.courser.courses.models.Teacher;

import java.util.Set;
import java.util.stream.Collectors;

public class TeacherDTO {
    private long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Boolean active;
    private Set<TeacherCourseDTO> teacherCourses;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.fullName = teacher.getFullName();
        this.email = teacher.getEmail();
        this.password = teacher.getPassword();
        this.role = teacher.getRole();
        this.active = teacher.getActive();
        this.teacherCourses = teacher.getTeacherCourses().stream().map(TeacherCourseDTO::new).collect(Collectors.toSet());
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

    public Set<TeacherCourseDTO> getTeacherCourses() {
        return teacherCourses;
    }
}
