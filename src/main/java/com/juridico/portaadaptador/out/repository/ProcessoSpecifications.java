package com.juridico.portaadaptador.out.repository;

import com.juridico.dominio.model.enums.StatusProcesso;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ProcessoSpecifications {

    private ProcessoSpecifications() {
    }

    public static Specification<ProcessoEntity> comFiltro(String cpfCnpj, StatusProcesso status, LocalDate dataDeAbertura) {
        return (root, _, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (status != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }
            if (dataDeAbertura != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dataDeAbertura"), dataDeAbertura));
            }
            if (cpfCnpj != null && !cpfCnpj.isBlank()) {
                var joinParte = root.join("partesEnvolvidas");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(joinParte.get("cpfCnpj"), cpfCnpj));
            }
            return predicate;
        };
    }
}
