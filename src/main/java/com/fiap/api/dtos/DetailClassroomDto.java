package com.fiap.api.dtos;

import com.fiap.api.domain.Classroom;

public record DetailClassroomDto(String id, String name, String localization) {
    public DetailClassroomDto(Classroom classroom) {
        this(classroom.getId(), classroom.getName(), classroom.getLocalization());
    }
}
