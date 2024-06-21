package com.fiap.api.repositories;

import com.fiap.api.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,String> {
    List<Professor> findAllByIsActiveTrue();

    Professor getByName(String name);

    Professor findBySchoolSubjectNameAndIsActiveTrue(String subjectName);

    Professor getByNameAndIsActiveTrue(String name);
}
