package com.juridico.aplicacao.service;

import com.juridico.comum.enums.StatusProcesso;
import com.juridico.comum.mapper.ProcessoMapper;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import com.juridico.portaadaptador.repository.ProcessoRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultorDeProcessoServiceImplTest {

    @Mock
    private ProcessoRepository processoRepository;
    @Mock
    private ProcessoMapper processoMapper;
    @Captor
    private ArgumentCaptor<ProcessoEntity> argumentCaptor;
    @InjectMocks
    private ConsultorDeProcessoServiceImpl consultorDeProcessoService;
    private String cpfCnpj;
    private StatusProcesso status;
    private LocalDate dataDeAbertura;

    @BeforeEach
    void init() {
        cpfCnpj = Instancio.of(String.class).create();
        status = Instancio.of(StatusProcesso.class).create();
        dataDeAbertura = Instancio.of(LocalDate.class).create();
    }

    @Test
    void deve_buscar_o_processo() {
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        when(processoRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(processoEntity));
        Processo processo = Instancio.of(Processo.class).create();
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processo);

        consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura);

        verify(processoRepository).findAll(any(Specification.class));
    }

    @Test
    void deve_mapear_o_processo_buscado() {
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        when(processoRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(processoEntity));

        consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura);

        verify(processoMapper).paraDominio(argumentCaptor.capture());
        ProcessoEntity processoEnviadoParaMapear = argumentCaptor.getValue();
        Assertions.assertEquals(processoEntity.getId(), processoEnviadoParaMapear.getId());
        Assertions.assertEquals(processoEntity.getDataDeAbertura(), processoEnviadoParaMapear.getDataDeAbertura());
        Assertions.assertEquals(processoEntity.getDescricaoDoCaso(), processoEnviadoParaMapear.getDescricaoDoCaso());
    }

    @Test
    void deve_retornar_o_processo_mapeado() {
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        Processo processoMapeado = Instancio.of(Processo.class).create();
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processoMapeado);
        when(processoRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(processoEntity));

        Processo processoRetornado = consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura)
                .stream().findFirst().orElseThrow();

        Assertions.assertEquals(processoMapeado.getId(), processoRetornado.getId());
        Assertions.assertEquals(processoMapeado.getDataDeAbertura(), processoRetornado.getDataDeAbertura());
        Assertions.assertEquals(processoMapeado.getDescricaoDoCaso(), processoRetornado.getDescricaoDoCaso());
    }

    @Test
    void deve_retornar_erro_caso_nao_exista_processo() {
        when(processoRepository.findAll(any(Specification.class))).thenReturn(new ArrayList());

        Executable action = () -> consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura);

        Assertions.assertThrows(NoSuchElementException.class, action, "Não foi encontrado o processo.");
    }

}