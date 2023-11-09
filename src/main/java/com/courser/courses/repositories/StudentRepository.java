package com.courser.courses.repositories;

import com.courser.courses.models.subclass.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findByFullName(String fullName);
    boolean existsByEmail(String email);
}
