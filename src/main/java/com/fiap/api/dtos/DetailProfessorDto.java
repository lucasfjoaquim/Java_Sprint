package com.fiap.api.dtos;

import com.fiap.api.domain.Professor;
import com.fiap.api.domain.SchoolSubject;

import java.time.LocalDateTime;

public record DetailProfessorDto(String id, String name, Double Salary, String phone, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive, SchoolSubject schoolSubject) {

    public DetailProfessorDto(Professor professor){
        this(professor.getId(), professor.getName(), professor.getSalary(), professor.getPhone(), professor.getStartDate(),professor.getEndDate(),professor.getIsActive(),professor.getSchoolSubject());
    }

}
