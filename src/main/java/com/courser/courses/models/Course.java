package com.courser.courses.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String totalTime;
    private String description;
    private String schedule;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maximumQuota;
    private Integer currentQuota;
    private CourseShift shift;
    private String category;
    private Boolean active;

    @OneToMany(mappedBy="course", fetch= FetchType.EAGER)
    private Set<StudentCourse> studentCourses = new HashSet<>();

    @OneToMany(mappedBy="course", fetch= FetchType.EAGER)
    private Set<TeacherCourse> teacherCourses = new HashSet<>();

    public Course() {}

    public Course(String name, String totalTime, String description, String schedule, LocalDate startDate, LocalDate endDate, Integer maximumQuota, Integer currentQuota, CourseShift shift, String category, Boolean active) {
        this.name = name;
        this.totalTime = totalTime;
        this.description = description;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maximumQuota = maximumQuota;
        this.currentQuota = currentQuota;
        this.shift = shift;
        this.category = category;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getMaximumQuota() {
        return maximumQuota;
    }
    public void setMaximumQuota(Integer maximumQuota) {
        this.maximumQuota = maximumQuota;
    }

    public Integer getCurrentQuota() {
        return currentQuota;
    }
    public void setCurrentQuota(Integer currentQuota) {
        this.currentQuota = currentQuota;
    }

    public CourseShift getShift() {
        return shift;
    }
    public void setShift(CourseShift shift) {
        this.shift = shift;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
    public void addStudentCourse(StudentCourse studentCourse) {
        studentCourse.setCourse(this);
        studentCourses.add(studentCourse);
    }

    public Set<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
    public void addTeacherCourse(TeacherCourse teacherCourse) {
        teacherCourse.setCourse(this);
        teacherCourses.add(teacherCourse);
    }
}
