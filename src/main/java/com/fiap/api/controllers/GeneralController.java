package com.fiap.api.controllers;

import com.fiap.api.domain.SchoolGeneral;
import com.fiap.api.dtos.CreateGeneralServiceDto;
import com.fiap.api.dtos.DetailGeneralServiceDto;
import com.fiap.api.services.GeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createGeneralService(@RequestBody CreateGeneralServiceDto generalServiceDto, UriComponentsBuilder componentsBuilder)throws Exception{
      SchoolGeneral general = generalService.createGeneralService(generalServiceDto);
      URI uri = componentsBuilder.path("/general/{id}").buildAndExpand(general.getId()).toUri();
      return ResponseEntity.created(uri).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DetailGeneralServiceDto>> getAllGeneral(){
        List<DetailGeneralServiceDto> generalServiceDtos = generalService.getAllGeneral();
        return ResponseEntity.ok().body(generalServiceDtos);
    }

    @GetMapping("/actives")
    public ResponseEntity<List<DetailGeneralServiceDto>> getAllGeneralActives(){
        List<DetailGeneralServiceDto> generalServiceDtos = generalService.getAllGeneralActives();
        return ResponseEntity.ok().body(generalServiceDtos);
    }

    @GetMapping("/value/{id}")
    public ResponseEntity<DetailGeneralServiceDto> getGeneralById(@PathVariable String id)throws Exception{
        DetailGeneralServiceDto generalServiceDto = generalService.getGeneralById(id);
        return ResponseEntity.ok().body(generalServiceDto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DetailGeneralServiceDto> getGeneralByName(@PathVariable String name)throws Exception{
        DetailGeneralServiceDto generalServiceDto = generalService.getGeneralByName(name);
        return ResponseEntity.ok().body(generalServiceDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateGeneral(@PathVariable String id, @RequestBody CreateGeneralServiceDto serviceDto)throws Exception{
        generalService.updateGeneral(id,serviceDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> disableGenetal(@PathVariable String id)throws Exception{
        generalService.desactiveGeneral(id);
        return ResponseEntity.noContent().build();
    }
}
