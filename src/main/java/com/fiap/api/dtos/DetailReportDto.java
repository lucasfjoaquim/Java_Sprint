package com.fiap.api.dtos;

import com.fiap.api.domain.SchoolGrades;
import com.fiap.api.domain.SchoolReport;
import com.fiap.api.domain.Student;

import java.time.LocalDateTime;
import java.util.Set;

public record DetailReportDto(String id, String semester, Boolean isActive, LocalDateTime startDate, LocalDateTime endDate, Set<SchoolGrades> grades , Student student) {

    public DetailReportDto(SchoolReport report){
        this(report.getId(),report.getSemester(),report.getIsActive(),report.getStartDate(),report.getEndDate(),report.getSchoolGrades(),report.getStudent());
    }
}
