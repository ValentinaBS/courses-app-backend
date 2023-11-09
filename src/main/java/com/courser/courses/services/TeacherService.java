package com.courser.courses.services;

import com.courser.courses.dtos.TeacherDTO;
import com.courser.courses.models.subclass.Teacher;

import java.util.Set;

public interface TeacherService {
    Teacher findByEmail(String email);
    Teacher findById(Long id);
    boolean existsByEmail(String email);

    TeacherDTO getTeacherDTO(Long id);
    Set<TeacherDTO> getTeachersDTO();
    TeacherDTO getCurrentTeacher(String email);
    void saveTeacher(Teacher teacher);
}
