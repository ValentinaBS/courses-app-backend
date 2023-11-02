package com.courser.courses.services.implement;

import com.courser.courses.dtos.StudentDTO;
import com.courser.courses.models.Course;
import com.courser.courses.models.Student;
import com.courser.courses.repositories.StudentRepository;
import com.courser.courses.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class StudentServiceImplement implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public Student findByFullName(String fullName) {
        return studentRepository.findByFullName(fullName);
    }

    @Override
    public StudentDTO getStudentDTO(Long id) {
        return new StudentDTO(this.findById(id));
    }

    @Override
    public Set<StudentDTO> getStudentsDTO() {
        return studentRepository
                .findAll()
                .stream()
                .map(StudentDTO::new)
                .collect(toSet());
    }

    @Override
    public StudentDTO getCurrentStudent(String email) {
        return new StudentDTO(this.findByEmail(email));
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}
