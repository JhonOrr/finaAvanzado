package com.example.msorregoJesus.domain.impl;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;
import com.example.msorregoJesus.domain.ports.in.PersonaServiceIn;
import com.example.msorregoJesus.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDto crearPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDto> obtenerPersonaIn(String dni) {
        return personaServiceOut.obtenerPersonaOut(dni);
    }

    public List<PersonaDto> obtenerTodosIn(){
        return personaServiceOut.obtenerTodosOut();
    }
    public PersonaDto actualizarIn(Long id, RequestPersona requestPersona){
        return personaServiceOut.actualizarOut(id, requestPersona);
    }
}
