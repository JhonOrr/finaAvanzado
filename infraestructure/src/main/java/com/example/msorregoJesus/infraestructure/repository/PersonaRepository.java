package com.example.msorregoJesus.infraestructure.repository;

import com.example.msorregoJesus.infraestructure.entity.PersonaEntity;
import com.example.msorregoJesus.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByNumDocu(@Param("numDocu") String numDocu);
    List<PersonaEntity> findByEstado(Integer estado);
}
