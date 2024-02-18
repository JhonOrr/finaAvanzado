package com.example.msorregoJesus.infraestructure.mapper;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class PersonaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public PersonaDto mapToDto(PersonaEntity entity){
        return modelMapper.map(entity, PersonaDto.class);
    }
    public PersonaEntity mapToEntity(PersonaDto personaDto){
        return modelMapper.map(personaDto, PersonaEntity.class);
    }
}
