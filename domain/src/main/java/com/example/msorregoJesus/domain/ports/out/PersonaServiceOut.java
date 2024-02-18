package com.example.msorregoJesus.domain.ports.out;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDto crearPersonaOut(RequestPersona requestPersona);
    Optional<PersonaDto> obtenerPersonaOut(String dni);
    List<PersonaDto> obtenerTodosOut();
    PersonaDto actualizarOut(Long id, RequestPersona requestPersona);
    PersonaDto deleteOut(Long id);
}
