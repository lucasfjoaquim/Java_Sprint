package com.fiap.api.repositories;

import com.fiap.api.domain.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, String> {
    SchoolSubject getByName(String name);
}
