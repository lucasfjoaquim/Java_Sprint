package com.fiap.api.dtos;

import com.fiap.api.domain.SchoolGrades;
import com.fiap.api.domain.SchoolReport;
import com.fiap.api.domain.SchoolSubject;

public record DetailGradesDto(String id, Double firstGrade, Double secondGrade, SchoolSubject schoolSubject, SchoolReport schoolReport) {

    public DetailGradesDto(SchoolGrades grades){
        this(grades.getId(), grades.getFirstGrade(), grades.getSecondGrade(), grades.getSchoolSubject(),grades.getReport());
    }

}
