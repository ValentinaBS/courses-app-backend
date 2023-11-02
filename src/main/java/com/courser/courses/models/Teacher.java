package com.courser.courses.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Boolean active;

    @OneToMany(mappedBy="teacher", fetch= FetchType.EAGER)
    private Set<TeacherCourse> teacherCourses = new HashSet<>();

    public Teacher() {}

    public Teacher(String fullName, String email, String password, Role role, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public void addTeacherCourse(TeacherCourse teacherCourse) {
        teacherCourse.setTeacher(this);
        teacherCourses.add(teacherCourse);
    }
}
