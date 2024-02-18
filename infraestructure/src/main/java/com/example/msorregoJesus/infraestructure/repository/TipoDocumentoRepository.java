package com.example.msorregoJesus.infraestructure.repository;

import com.example.msorregoJesus.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {
    TipoDocumentoEntity findByCodTipo(String codTipo);
}


