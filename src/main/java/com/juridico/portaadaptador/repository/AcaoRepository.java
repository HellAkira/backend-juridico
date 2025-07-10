package com.juridico.portaadaptador.repository;

import com.juridico.portaadaptador.entity.AcaoEntity;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoEntity, Long> {
}
