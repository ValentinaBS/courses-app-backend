package com.courser.courses.dtos;

import com.courser.courses.models.StudentCourse;

public class StudentCourseDTO {
    private long id;
    private long studentId;
    private long courseId;
    private boolean completed;

    public StudentCourseDTO(StudentCourse studentCourse) {
        this.id = studentCourse.getId();
        this.studentId = studentCourse.getStudent().getId();
        this.courseId = studentCourse.getCourse().getId();
        this.completed = studentCourse.isCompleted();
    }

    public long getId() {
        return id;
    }

    public long getStudentId() {
        return studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
