package com.fiap.api.repositories;

import com.fiap.api.domain.SchoolReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolReportRepository extends JpaRepository<SchoolReport,String> {
   List<SchoolReport> findAllReportByStudentName(String name);

   List<SchoolReport>  findAllReportByisActiveTrueAndStudentName(String name);

    List<SchoolReport> findAllBySemester(String semester);

    SchoolReport findBySemesterAndStudentName( String semester,String studentName);

    SchoolReport findReportBySemesterAndStudentNameAndSchoolGradesSchoolSubjectName(String reportSemester, String studentName, String subjectName);
}
