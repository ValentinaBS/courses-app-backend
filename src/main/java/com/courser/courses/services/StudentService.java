package com.courser.courses.services;

import com.courser.courses.dtos.StudentDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.Student;

import java.util.Set;

public interface StudentService {
    Student findByEmail(String email);
    Student findById(Long id);
    boolean existsByEmail(String email);

    Student findByFullName(String fullName);
    StudentDTO getStudentDTO(Long id);
    Set<StudentDTO> getStudentsDTO();
    StudentDTO getCurrentStudent(String email);
    void saveStudent(Student student);
}
