package com.fiap.api.services;

import com.fiap.api.domain.SchoolSubject;
import com.fiap.api.dtos.CreateSubjectDto;
import com.fiap.api.dtos.DetailSubjectDto;
import com.fiap.api.infra.exceptions.SubjectNotFoundException;
import com.fiap.api.repositories.SchoolSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolSubjectService {

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;

    public SchoolSubject createSchoolSubject(CreateSubjectDto subjectDto){
       return schoolSubjectRepository.save(new SchoolSubject(subjectDto));
    }

    public List<DetailSubjectDto> getAllSubjects(){
        return schoolSubjectRepository.findAll().stream().map(DetailSubjectDto::new).collect(Collectors.toList());
    }

    public DetailSubjectDto getSubjectByName(String name) throws SubjectNotFoundException {
        SchoolSubject subject = schoolSubjectRepository.getByName(name);
        if(subject == null){
            throw new SubjectNotFoundException();
        }
        return new DetailSubjectDto(subject);
    }

    public DetailSubjectDto getSubjectById(String id) throws SubjectNotFoundException {
        SchoolSubject subject = schoolSubjectRepository.findById(id).orElseThrow(()-> new SubjectNotFoundException());
        return new DetailSubjectDto(subject);
    }


    public void updateSubject(String id, CreateSubjectDto subjectDto) throws SubjectNotFoundException {
        SchoolSubject subject = schoolSubjectRepository.findById(id).orElseThrow(()-> new SubjectNotFoundException());
        subject.updateSubject(subjectDto);
        schoolSubjectRepository.save(subject);
    }


}
