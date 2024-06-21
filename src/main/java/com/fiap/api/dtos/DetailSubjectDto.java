package com.fiap.api.dtos;

import com.fiap.api.domain.Professor;
import com.fiap.api.domain.SchoolSubject;

public record DetailSubjectDto(String id, String name, Double cargaHoraria, Professor professor) {

    public DetailSubjectDto(SchoolSubject subject){
        this(subject.getId(),subject.getName(),subject.getCargaHoraria(),subject.getProfessor());
    }
}
