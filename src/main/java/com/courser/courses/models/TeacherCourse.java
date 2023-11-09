package com.courser.courses.models;

import com.courser.courses.models.subclass.Teacher;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private boolean completed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public TeacherCourse() {}
    public TeacherCourse(boolean completed) {
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

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
