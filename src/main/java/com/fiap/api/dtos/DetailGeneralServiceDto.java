package com.fiap.api.dtos;

import com.fiap.api.domain.Classroom;
import com.fiap.api.domain.SchoolGeneral;

import java.time.LocalDateTime;

public record DetailGeneralServiceDto(String id, String name, Double Salary,
                                      String phone, LocalDateTime startDate,
                                      LocalDateTime endDate, Boolean isActive,  Classroom classroom) {

    public DetailGeneralServiceDto(SchoolGeneral general){
        this(general.getId(), general.getName(), general.getSalary(),
                general.getPhone(), general.getStartDate(),general.getEndDate(),
                general.getIsActive(), general.getClassroom());
    }

}
