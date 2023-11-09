package com.courser.courses.models.subclass;

import com.courser.courses.models.enums.Role;
import com.courser.courses.models.StudentCourse;
import com.courser.courses.models.supclass.Person;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Person {

    private String description;

    @OneToMany(mappedBy="student", fetch= FetchType.EAGER)
    private Set<StudentCourse> studentCourses = new HashSet<>();

    public Student() {}

    public Student(String fullName, String email, String password, Role role, Boolean active, String description) {
        super(fullName, email, password, role, active);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }
    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
    public void addStudentCourse(StudentCourse studentCourse) {
        studentCourse.setStudent(this);
        studentCourses.add(studentCourse);
    }
}
