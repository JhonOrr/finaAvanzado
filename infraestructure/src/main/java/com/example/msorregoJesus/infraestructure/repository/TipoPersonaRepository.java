package com.example.msorregoJesus.infraestructure.repository;

import com.example.msorregoJesus.infraestructure.entity.TipoDocumentoEntity;
import com.example.msorregoJesus.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long>{
    TipoPersonaEntity findByCodTipo(@Param("codTipo") String codTipo);
}
