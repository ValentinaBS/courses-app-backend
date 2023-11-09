package com.courser.courses.models.subclass;

import com.courser.courses.models.enums.Role;
import com.courser.courses.models.TeacherCourse;
import com.courser.courses.models.supclass.Person;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends Person {

    private String major;

    @OneToMany(mappedBy="teacher", fetch= FetchType.EAGER)
    private Set<TeacherCourse> teacherCourses = new HashSet<>();

    public Teacher() {}

    public Teacher(String fullName, String email, String password, Role role, Boolean active, String major) {
        super(fullName, email, password, role, active);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
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
