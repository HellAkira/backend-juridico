package com.juridico.portaadaptador.out.repository;

import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoEntity, Long>, JpaSpecificationExecutor<ProcessoEntity> {
}
