package com.courser.courses.services.implement;

import com.courser.courses.dtos.TeacherDTO;
import com.courser.courses.models.subclass.Teacher;
import com.courser.courses.repositories.TeacherRepository;
import com.courser.courses.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class TeacherServiceImplement implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return teacherRepository.existsByEmail(email);
    }

    @Override
    public TeacherDTO getTeacherDTO(Long id) {
        return new TeacherDTO(this.findById(id));
    }

    @Override
    public Set<TeacherDTO> getTeachersDTO() {
        return teacherRepository
                .findAll()
                .stream()
                .map(TeacherDTO::new)
                .collect(toSet());
    }

    @Override
    public TeacherDTO getCurrentTeacher(String email) {
        return new TeacherDTO(this.findByEmail(email));
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
