package com.courser.courses.dtos;

import com.courser.courses.models.TeacherCourse;

public class TeacherCourseDTO {
    private long id;
    private long teacherId;
    private long courseId;
    private boolean completed;

    public TeacherCourseDTO(TeacherCourse teacherCourse) {
        this.id = teacherCourse.getId();
        this.teacherId = teacherCourse.getTeacher().getId();
        this.courseId = teacherCourse.getCourse().getId();
        this.completed = teacherCourse.isCompleted();
    }

    public long getId() {
        return id;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public long getCourseId() {
        return courseId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
