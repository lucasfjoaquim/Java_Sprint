package com.fiap.api.repositories;

import com.fiap.api.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findStudentByIsActiveTrue();

    Student findByName(String name);
}
