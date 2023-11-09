package com.courser.courses.dtos;

import com.courser.courses.models.StudentCourse;

public class StudentCourseDTO {
    private long id;
    private String studentName;
    private String courseName;
    private boolean completed;

    public StudentCourseDTO(StudentCourse studentCourse) {
        this.id = studentCourse.getId();
        this.studentName = studentCourse.getStudent().getFullName();
        this.courseName = studentCourse.getCourse().getName();
        this.completed = studentCourse.isCompleted();
    }

    public long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isCompleted() {
        return completed;
    }
}
