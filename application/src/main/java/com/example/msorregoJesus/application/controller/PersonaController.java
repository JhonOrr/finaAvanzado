package com.example.msorregoJesus.application.controller;

import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;
import com.example.msorregoJesus.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info=@Info(
                title="API-PERSONA",
                version="1.0",
                description="Mantenimiento de una Persona"
        )
)




@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @Operation(summary = "Crear una persona")
    @PostMapping
    public ResponseEntity<PersonaDto> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }
    @Operation(summary = "Obtener una persona por id")
    @GetMapping("/{dni}")
    public ResponseEntity<PersonaDto> obtenerPersona(@PathVariable String dni){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(dni).get());
    }

    @Operation(summary="Obtener todas las personas")
    @GetMapping
    public ResponseEntity<List<PersonaDto>>obtenerTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());
    }

    @Operation(summary="Actualizar una persona")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto>actualizar(@PathVariable Long id, @RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarIn(id, requestPersona));
    }

    @Operation(summary = "Eliminar un persona por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDto>eliminar(@PathVariable Long id){
        return ResponseEntity
                .status((HttpStatus.OK))
                .body(personaServiceIn.deleteIn(id));
    }
}
