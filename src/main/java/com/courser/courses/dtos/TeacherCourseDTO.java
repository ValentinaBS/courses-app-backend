package com.courser.courses.dtos;

import com.courser.courses.models.TeacherCourse;

public class TeacherCourseDTO {
    private long id;
    private String teacherName;
    private String courseName;
    private boolean completed;

    public TeacherCourseDTO(TeacherCourse teacherCourse) {
        this.id = teacherCourse.getId();
        this.teacherName = teacherCourse.getTeacher().getFullName();
        this.courseName = teacherCourse.getCourse().getName();
        this.completed = teacherCourse.isCompleted();
    }

    public long getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isCompleted() {
        return completed;
    }
}
