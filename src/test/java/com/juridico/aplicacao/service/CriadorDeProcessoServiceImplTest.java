package com.juridico.aplicacao.service;

import com.juridico.aplicacao.dto.ProcessoParams;
import com.juridico.aplicacao.interfaces.SalvadorDeProcesso;
import com.juridico.dominio.model.Processo;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriadorDeProcessoServiceImplTest {

    @Mock
    private SalvadorDeProcesso salvadorDeProcesso;

    @InjectMocks
    private CriadorDeProcessoServiceImpl criadorDeProcessoService;

    private ProcessoParams processoParams;

    @BeforeEach
    void setUp() {
        processoParams = Instancio.of(ProcessoParams.class).create();
    }

    @Test
    void deve_salvar_processo() {

        criadorDeProcessoService.criarProcesso(processoParams);

        verify(salvadorDeProcesso).salvar(any(Processo.class));
    }

    @Test
    void deve_retornar_processo_salvo() {
        Processo processo = Instancio.of(Processo.class).create();
        when(salvadorDeProcesso.salvar(any(Processo.class))).thenReturn(processo);

        Processo processoRetornado = criadorDeProcessoService.criarProcesso(processoParams);

        Assertions.assertNotNull(processoRetornado);
        Assertions.assertEquals(processo, processoRetornado);
    }
}