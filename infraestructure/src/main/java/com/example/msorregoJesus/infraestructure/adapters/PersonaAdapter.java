package com.example.msorregoJesus.infraestructure.adapters;

import com.example.msorregoJesus.domain.aggregates.constants.Constants;
import com.example.msorregoJesus.domain.aggregates.dto.PersonaDto;
import com.example.msorregoJesus.domain.aggregates.request.RequestPersona;
import com.example.msorregoJesus.domain.aggregates.response.ResponseReniec;
import com.example.msorregoJesus.domain.ports.out.PersonaServiceOut;
import com.example.msorregoJesus.infraestructure.entity.PersonaEntity;
import com.example.msorregoJesus.infraestructure.entity.TipoDocumentoEntity;
import com.example.msorregoJesus.infraestructure.entity.TipoPersonaEntity;
import com.example.msorregoJesus.infraestructure.mapper.PersonaMapper;
import com.example.msorregoJesus.infraestructure.repository.PersonaRepository;
import com.example.msorregoJesus.infraestructure.repository.TipoDocumentoRepository;
import com.example.msorregoJesus.infraestructure.repository.TipoPersonaRepository;
import com.example.msorregoJesus.infraestructure.rest.client.ReniecClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final PersonaMapper personaMapper;
    private final ReniecClient reniec;
    private final TipoPersonaRepository tipoPersonaRepository;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public PersonaDto crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
//        personaRepository.save(getEntity(datosReniec,requestPersona));
//        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(personaRepository.save(getEntity(datosReniec,requestPersona)));
    }

    public Optional<PersonaDto> obtenerPersonaOut(String dni){
        PersonaDto personaDto = personaMapper.mapToDto(personaRepository.findByNumDocu(dni).get());
        return Optional.of(personaDto);
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero, authorization);
        return responseReniec;
    }

    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    public List<PersonaDto> obtenerTodosOut(){
        List<PersonaDto> personaDtoList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findByEstado(1);
        for(PersonaEntity persona : entities){
            PersonaDto personaDto = personaMapper.mapToDto(persona);
            personaDtoList.add(personaDto);
        }
        return personaDtoList;
    }

    public PersonaDto actualizarOut(Long id, RequestPersona requestPersona){
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec, entity.get(), requestPersona));
            return personaMapper.mapToDto(getEntityUpdate(responseReniec,entity.get(), requestPersona));
        }
        return null;
    }

    @Override
    public PersonaDto deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDto(entity.get());
        }
        return null;
    }


    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar, RequestPersona requestPersona){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        personaActualizar.setTipoPersona(tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer()));
        return personaActualizar;
    }

}
