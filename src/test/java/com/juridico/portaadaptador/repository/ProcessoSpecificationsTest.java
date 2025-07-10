package com.juridico.portaadaptador.repository;

import com.juridico.dominio.model.enums.StatusProcesso;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import com.juridico.portaadaptador.out.repository.ProcessoSpecifications;
import jakarta.persistence.criteria.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessoSpecificationsTest {

    @Mock
    private Root<ProcessoEntity> root;
    @Mock
    private CriteriaQuery<?> query;
    @Mock
    private CriteriaBuilder cb;
    @Mock
    private Predicate predicate;
    private LocalDate dataDeAbertura;
    private String cpfCnpj;
    private StatusProcesso status;

    @BeforeEach
    void init() {
        dataDeAbertura = Instancio.of(LocalDate.class).create();
        cpfCnpj = Instancio.of(String.class).create();
        status = Instancio.of(StatusProcesso.class).create();
    }

    @Test
    void deve_adicionar_campo_cnpj() {
        when(cb.conjunction()).thenReturn(predicate);
        Join<Object, Object> joinMock = mock(Join.class);
        Path<Object> cpfPath = mock(Path.class);
        Predicate equalPredicate = mock(Predicate.class);
        Predicate combinedPredicate = mock(Predicate.class);
        when(root.join("partesEnvolvidas")).thenReturn(joinMock);
        when(joinMock.get("cpfCnpj")).thenReturn(cpfPath);
        when(cb.equal(cpfPath, cpfCnpj)).thenReturn(equalPredicate);
        when(cb.and(any(), eq(equalPredicate))).thenReturn(combinedPredicate);

        Specification<ProcessoEntity> specification = ProcessoSpecifications.comFiltro(cpfCnpj, null, null);
        specification.toPredicate(root, query, cb);

        verify(root).join("partesEnvolvidas");
        verify(joinMock).get("cpfCnpj");
        verify(cb).equal(cpfPath, cpfCnpj);
    }

    @Test
    void deve_adicionar_campo_status() {
        when(cb.conjunction()).thenReturn(predicate);
        when(root.get("status")).thenReturn(mock(Path.class));
        when(cb.equal(any(), eq(status))).thenReturn(mock(Predicate.class));
        when(cb.and(any(), any())).thenReturn(mock(Predicate.class));

        Specification<ProcessoEntity> specification = ProcessoSpecifications.comFiltro(null, status, null);
        specification.toPredicate(root, query, cb);

        verify(root).get("status");
        verify(cb).equal(any(), eq(status));
    }

    @Test
    void deve_adicionar_campo_data_abertura() {
        when(cb.conjunction()).thenReturn(predicate);
        when(root.get("dataDeAbertura")).thenReturn(mock(Path.class));
        when(cb.equal(any(), eq(dataDeAbertura))).thenReturn(mock(Predicate.class));
        when(cb.and(any(), any())).thenReturn(mock(Predicate.class));

        Specification<ProcessoEntity> specification = ProcessoSpecifications.comFiltro(null, null, dataDeAbertura);
        specification.toPredicate(root, query, cb);

        verify(root).get("dataDeAbertura");
        verify(cb).equal(any(), eq(dataDeAbertura));
    }

    @ParameterizedTest
    @CsvSource({
            "'', , , cpfCnpj",
            ", , , status",
            ", , , dataDeAbertura"
    })
    void nao_deve_adicionar_campo_quando_parametro_for_invalido(String cpfCnpj, StatusProcesso status, LocalDate dataAbertura, String campoEsperado) {
        when(cb.conjunction()).thenReturn(predicate);

        Specification<ProcessoEntity> specification = ProcessoSpecifications.comFiltro(cpfCnpj, status, dataAbertura);
        specification.toPredicate(root, query, cb);

        verify(root, never()).get(campoEsperado);
        verify(cb, never()).equal(any(), any());
    }
}