package com.courser.courses.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private boolean completed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public StudentCourse() {}
    public StudentCourse(boolean completed) {
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
