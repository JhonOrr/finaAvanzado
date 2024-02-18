package com.example.msorregoJesus.application.controller;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;
import com.example.msorregoJesus.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDto> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @GetMapping("/{dni}")
    public ResponseEntity<PersonaDto> obtenerPersona(@PathVariable String dni){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(dni).get());
    }
    @GetMapping
    public ResponseEntity<List<PersonaDto>>obtenerTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto>actualizar(@PathVariable Long id, @RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarIn(id, requestPersona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDto>eliminar(@PathVariable Long id){
        return ResponseEntity
                .status((HttpStatus.OK))
                .body(personaServiceIn.deleteIn(id));
    }
}
