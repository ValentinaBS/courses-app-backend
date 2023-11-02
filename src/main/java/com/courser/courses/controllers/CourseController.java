package com.courser.courses.controllers;

import com.courser.courses.dtos.CourseDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.Teacher;
import com.courser.courses.services.CourseService;
import com.courser.courses.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/courses")
    public Set<CourseDTO> getCourses() {
        return courseService.getCoursesDTO();
    }

    @PostMapping("/courses")
    public ResponseEntity<Object> createCourse(@RequestBody CourseDTO courseDTO) {

        if (courseService.existsByName(courseDTO.getName())) {
            return new ResponseEntity<>("There is an existing course with the same name.", HttpStatus.FORBIDDEN);
        }

        courseService.saveCourse(new Course(courseDTO.getName(), courseDTO.getTotalTime(), courseDTO.getDescription(),
                courseDTO.getSchedule(), courseDTO.getStartDate(), courseDTO.getEndDate(), courseDTO.getMaximumQuota(),
                courseDTO.getCurrentQuota(), courseDTO.getShift(), courseDTO.getCategory(), true));
        return new ResponseEntity<>("A new course has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/courses/update/{id}")
    public ResponseEntity<Object> updateCourse(
            @RequestBody Course newCourse,
            @PathVariable Long id) {

        Course oldCourse = courseService.findById(id);
        if (oldCourse == null) {
            return new ResponseEntity<>("Couldn't find a course to update with that id.", HttpStatus.FORBIDDEN);
        }

        oldCourse.setName(newCourse.getName());
        oldCourse.setTotalTime(newCourse.getTotalTime());
        oldCourse.setDescription(newCourse.getDescription());
        oldCourse.setSchedule(newCourse.getSchedule());
        oldCourse.setStartDate(newCourse.getStartDate());
        oldCourse.setEndDate(newCourse.getEndDate());
        oldCourse.setMaximumQuota(newCourse.getMaximumQuota());
        oldCourse.setCurrentQuota(newCourse.getCurrentQuota());
        oldCourse.setShift(newCourse.getShift());
        oldCourse.setCategory(newCourse.getCategory());
        oldCourse.setActive(newCourse.getActive());

        courseService.saveCourse(oldCourse);
        return ResponseEntity.ok("Updated course data");
    }

    @PatchMapping("/courses/remove/{id}")
    public ResponseEntity<Object> removeCourse(@PathVariable Long id) {

        Course course = courseService.findById(id);
        if(course == null) {
            return new ResponseEntity<>("This course doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if(!course.getActive()){
            return new ResponseEntity<>("This course is already removed", HttpStatus.FORBIDDEN);
        }

        course.setActive(false);
        courseService.saveCourse(course);

        return new ResponseEntity<>("Course removed successfully", HttpStatus.OK);
    }

}
