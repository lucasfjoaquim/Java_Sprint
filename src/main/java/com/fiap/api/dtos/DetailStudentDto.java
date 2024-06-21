package com.fiap.api.dtos;

import com.fiap.api.domain.SchoolReport;
import com.fiap.api.domain.Student;

import java.time.LocalDateTime;
import java.util.List;

public record DetailStudentDto(String id, String name, Boolean isActive, String phone, LocalDateTime startDate, LocalDateTime endDate, List<SchoolReport> schoolReports) {

    public DetailStudentDto(Student student){
        this(student.getId(), student.getName(), student.isActive(), student.getPhone(), student.getStartDate(),student.getEndDate(),student.getSchoolReport());
    }

}
