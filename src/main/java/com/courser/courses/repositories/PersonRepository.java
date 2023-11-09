package com.courser.courses.repositories;

import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.supclass.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    Person findByFullName(String fullName);
    boolean existsByEmail(String email);
}
