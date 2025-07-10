package com.juridico.portaadaptador.out.repository;

import com.juridico.portaadaptador.out.entity.ParteEnvolvidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteEnvolvidaRepository extends JpaRepository<ParteEnvolvidaEntity, Long> {
}
