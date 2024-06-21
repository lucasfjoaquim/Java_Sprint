package com.fiap.api.controllers;

import com.fiap.api.domain.SchoolGrades;
import com.fiap.api.dtos.CreateGradeDto;
import com.fiap.api.dtos.DetailGradesDto;
import com.fiap.api.dtos.UpdateGradeDto;
import com.fiap.api.services.SchoolGradesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class SchoolGradeController {

    @Autowired
    private SchoolGradesService schoolGradesService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createGrades(@RequestBody CreateGradeDto gradeDto, UriComponentsBuilder componentsBuilder)throws Exception{
        SchoolGrades grades = schoolGradesService.createGrades(gradeDto);
        URI uri = componentsBuilder.path("/grade/{id}").buildAndExpand(grades.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<DetailGradesDto>> getGradesByNameAndSemester(@RequestParam String name, @RequestParam String semester){
        List<DetailGradesDto> gradesDtos = schoolGradesService.getGradesByNameAndSemester(name,semester);
        return ResponseEntity.ok().body(gradesDtos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateGrades(@RequestBody UpdateGradeDto gradeDto, @PathVariable String id)throws Exception{
        schoolGradesService.updateGrades(gradeDto,id);
        return ResponseEntity.noContent().build();
    }


}
