package com.fiap.api.repositories;

import com.fiap.api.domain.Administration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdministrationRepository extends JpaRepository<Administration,String> {
    List<Administration> findAllByIsActiveTrue();

    List<Administration> findAllByFunctionalAndIsActiveTrue(String function);

    Administration getByName(String name);

    Administration findByNameAndIsActiveTrue(String name);
}
