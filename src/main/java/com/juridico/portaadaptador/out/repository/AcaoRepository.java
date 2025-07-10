package com.juridico.portaadaptador.out.repository;

import com.juridico.portaadaptador.out.entity.AcaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoEntity, Long> {
}
