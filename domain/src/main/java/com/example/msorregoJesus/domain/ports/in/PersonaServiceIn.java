package com.example.msorregoJesus.domain.ports.in;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;
import org.bouncycastle.cert.ocsp.Req;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDto crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDto> obtenerPersonaIn(String dni);
    List<PersonaDto> obtenerTodosIn();
    PersonaDto actualizarIn(Long id, RequestPersona requestPersona);
}
