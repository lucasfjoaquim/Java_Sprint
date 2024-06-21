package com.fiap.api.repositories;

import com.fiap.api.domain.SchoolGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralServiceRepository extends JpaRepository<SchoolGeneral,String> {
    List<SchoolGeneral> findAllByIsActiveTrue();

    SchoolGeneral getByName(String name);

    SchoolGeneral getByNameAndIsActiveTrue(String name);
}
