package com.fiap.api.services;

import com.fiap.api.domain.SchoolGrades;
import com.fiap.api.domain.SchoolReport;
import com.fiap.api.domain.SchoolSubject;
import com.fiap.api.dtos.CreateGradeDto;
import com.fiap.api.dtos.DetailGradesDto;
import com.fiap.api.dtos.UpdateGradeDto;
import com.fiap.api.infra.exceptions.*;
import com.fiap.api.repositories.SchoolGradesRepository;
import com.fiap.api.repositories.SchoolReportRepository;
import com.fiap.api.repositories.SchoolSubjectRepository;
import com.fiap.api.infra.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolGradesService {

    @Autowired
    private SchoolGradesRepository schoolGradesRepository;

    @Autowired
    private SchoolReportRepository schoolReportRepository;

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;

    public SchoolGrades createGrades(CreateGradeDto gradesDto) throws ReportNotFoundException, SubjectNotFoundException, IsAlreadyDesactivedException, DuplicatedSubjectException {
        SchoolReport report = schoolReportRepository.findBySemesterAndStudentName(gradesDto.reportSemester(), gradesDto.studentName());
        SchoolSubject subject = schoolSubjectRepository.getByName(gradesDto.subjectName());
        if(report == null){
            throw new ReportNotFoundException();
        }
        if(schoolReportRepository.findReportBySemesterAndStudentNameAndSchoolGradesSchoolSubjectName(gradesDto.reportSemester(),gradesDto.studentName(),gradesDto.subjectName()) != null){
            throw new DuplicatedSubjectException(gradesDto.subjectName());
        }
        if(!report.getStudent().isActive()){
            throw new IsAlreadyDesactivedException(report.getStudent().getName());
        }
        if(!report.getIsActive()){
            throw new IsAlreadyDesactivedException("report");
        }
        if(subject == null){
            throw new SubjectNotFoundException();
        }

        SchoolGrades grades = new SchoolGrades(gradesDto, report,subject);
        return schoolGradesRepository.save(grades);

    }

    public List<DetailGradesDto> getGradesByNameAndSemester(String name, String semester){
        return schoolGradesRepository.findAllByReportStudentNameAndReportSemester(name,semester).stream().map(DetailGradesDto::new).collect(Collectors.toList());
    }

    public void updateGrades(UpdateGradeDto gradeDto, String id) throws GradesNotFoundException {
        SchoolGrades grades = schoolGradesRepository.findById(id).orElseThrow(()->new GradesNotFoundException());
        grades.updateGrades(gradeDto);
        schoolGradesRepository.save(grades);
    }

}
