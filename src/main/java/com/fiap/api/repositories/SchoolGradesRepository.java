package com.fiap.api.repositories;

import com.fiap.api.domain.SchoolGrades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolGradesRepository extends JpaRepository<SchoolGrades,String> {
    List<SchoolGrades> findAllByReportStudentNameAndReportSemester(String studentName, String schoolReportSemester);
}
