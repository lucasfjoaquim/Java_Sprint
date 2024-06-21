package com.fiap.api.services;

import com.fiap.api.domain.Classroom;
import com.fiap.api.domain.SchoolGeneral;
import com.fiap.api.dtos.CreateGeneralServiceDto;
import com.fiap.api.dtos.DetailGeneralServiceDto;
import com.fiap.api.infra.exceptions.ClassroomNotFoundException;
import com.fiap.api.infra.exceptions.GeneralDuplicatedException;
import com.fiap.api.infra.exceptions.GeneralNotFoundException;
import com.fiap.api.infra.exceptions.IsAlreadyDesactivedException;
import com.fiap.api.repositories.ClassroomRepository;
import com.fiap.api.repositories.GeneralServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralService {

    @Autowired
    private GeneralServiceRepository generalServiceRepository;

    @Autowired
    private ClassroomRepository classroomRepository;


    public SchoolGeneral createGeneralService(CreateGeneralServiceDto generalDto) throws ClassroomNotFoundException, GeneralDuplicatedException {
        Classroom classroom = classroomRepository.getByName(generalDto.classroomName());
        SchoolGeneral general = generalServiceRepository.getByNameAndIsActiveTrue(generalDto.name());
        if(classroom == null){
            throw new ClassroomNotFoundException();
        }
        if(general != null){
            throw new GeneralDuplicatedException(general.getName());
        }
        SchoolGeneral schoolGeneral = new SchoolGeneral(generalDto,classroom);
        return generalServiceRepository.save(schoolGeneral);
    }

    public List<DetailGeneralServiceDto> getAllGeneral(){
        return generalServiceRepository.findAll().stream().map(DetailGeneralServiceDto::new).collect(Collectors.toList());
    }

    public List<DetailGeneralServiceDto> getAllGeneralActives(){
        return generalServiceRepository.findAllByIsActiveTrue().stream().map(DetailGeneralServiceDto::new).collect(Collectors.toList());
    }

    public DetailGeneralServiceDto getGeneralById(String id) throws GeneralNotFoundException {
        SchoolGeneral schoolGeneral = generalServiceRepository.findById(id).orElseThrow(()-> new GeneralNotFoundException());
        return new DetailGeneralServiceDto(schoolGeneral);
    }

    public DetailGeneralServiceDto getGeneralByName(String name) throws GeneralNotFoundException {
        SchoolGeneral schoolGeneral = generalServiceRepository.getByName(name);
        if(schoolGeneral == null){
            throw new GeneralNotFoundException();
        }
        return new DetailGeneralServiceDto(schoolGeneral);
    }

    public void updateGeneral(String id, CreateGeneralServiceDto generalDto) throws GeneralNotFoundException {
        System.out.println(id);
        SchoolGeneral general =generalServiceRepository.findById(id).orElseThrow(()->new GeneralNotFoundException());
        System.out.println(general);
        general.updateGeneral(generalDto);
        generalServiceRepository.save(general);
    }

    public void desactiveGeneral(String id) throws GeneralNotFoundException, IsAlreadyDesactivedException {
        SchoolGeneral general =generalServiceRepository.findById(id).orElseThrow(()-> new GeneralNotFoundException());
        if(!general.getIsActive()){
            throw new IsAlreadyDesactivedException(general.getName());
        }
        general.disableAnEmplooye();
        generalServiceRepository.save(general);
    }


}
