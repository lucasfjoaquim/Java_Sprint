package com.fiap.api.repositories;

import com.fiap.api.domain.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom,String> {
    Classroom getByName(String name);

}
