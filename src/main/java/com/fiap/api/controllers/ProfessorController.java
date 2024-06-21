package com.fiap.api.controllers;

import com.fiap.api.domain.Professor;
import com.fiap.api.dtos.CreateProfessorDto;
import com.fiap.api.dtos.DetailProfessorDto;
import com.fiap.api.dtos.UpdateProfessorDto;
import com.fiap.api.services.ProfessorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createProfessor(@RequestBody CreateProfessorDto professorDto, UriComponentsBuilder componentsBuilder) throws Exception{
        Professor professor = professorService.createProfessor(professorDto);
        URI uri = componentsBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DetailProfessorDto>> getAllProfessors(){
        List<DetailProfessorDto> professorDtos = professorService.getAllProfessors();
        return ResponseEntity.ok().body(professorDtos);
    }

    @GetMapping("/actives")
    public ResponseEntity<List<DetailProfessorDto>> getAllProfessorsActives(){
        List<DetailProfessorDto> professorDtos = professorService.getAllProfessorsActives();
        return ResponseEntity.ok().body(professorDtos);
    }

    @GetMapping("value/{id}")
    public ResponseEntity<DetailProfessorDto> getProfessorById(@PathVariable String id)throws Exception{
        DetailProfessorDto professorDto = professorService.getProfessorById(id);
        return ResponseEntity.ok().body(professorDto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DetailProfessorDto> getProfessorByName(@PathVariable String name)throws Exception{
        DetailProfessorDto professorDto = professorService.getProfessorByName(name);
        return ResponseEntity.ok().body(professorDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateProfessor(@PathVariable String id, @RequestBody UpdateProfessorDto professorDto)throws Exception{
        professorService.updateProfessor(id,professorDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> disableProfessor(@PathVariable String id)throws Exception{
        professorService.disableProfessor(id);
        return ResponseEntity.noContent().build();
    }


}
