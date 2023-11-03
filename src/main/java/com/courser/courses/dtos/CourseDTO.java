package com.courser.courses.dtos;

import com.courser.courses.models.Course;
import com.courser.courses.models.CourseShift;

import java.time.LocalDate;

public class CourseDTO {
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

    public CourseDTO() {}
    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.totalTime = course.getTotalTime();
        this.description = course.getDescription();
        this.schedule = course.getSchedule();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.maximumQuota = course.getMaximumQuota();
        this.currentQuota = course.getCurrentQuota();
        this.shift = course.getShift();
        this.category = course.getCategory();
        this.active = course.getActive();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getMaximumQuota() {
        return maximumQuota;
    }

    public Integer getCurrentQuota() {
        return currentQuota;
    }

    public CourseShift getShift() {
        return shift;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getActive() {
        return active;
    }
}
