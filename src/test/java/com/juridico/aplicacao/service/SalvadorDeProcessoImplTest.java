package com.juridico.aplicacao.service;

import com.juridico.comum.mapper.ProcessoMapper;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import com.juridico.portaadaptador.repository.ProcessoRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalvadorDeProcessoImplTest {

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private ProcessoMapper processoMapper;

    @InjectMocks
    private SalvadorDeProcessoImpl salvadorDeProcesso;

    private Processo processo;
    private ProcessoEntity processoEntity;

    @BeforeEach
    void setUp() {
        processo = Instancio.of(Processo.class).create();
        processoEntity = Instancio.of(ProcessoEntity.class).create();
    }

    @Test
    void deve_mapear_processo_para_entidade() {
        when(processoMapper.paraEntidade(processo)).thenReturn(processoEntity);
        when(processoRepository.save(processoEntity)).thenReturn(processoEntity);
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processo);

        salvadorDeProcesso.salvar(processo);

        verify(processoMapper).paraEntidade(processo);
    }

    @Test
    void deve_salvar_entidade() {
        when(processoMapper.paraEntidade(processo)).thenReturn(processoEntity);
        when(processoRepository.save(processoEntity)).thenReturn(processoEntity);
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processo);

        salvadorDeProcesso.salvar(processo);

        verify(processoRepository).save(processoEntity);
    }

    @Test
    void deve_mapear_entidade_para_processo() {
        when(processoMapper.paraEntidade(processo)).thenReturn(processoEntity);
        when(processoRepository.save(processoEntity)).thenReturn(processoEntity);
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processo);

        salvadorDeProcesso.salvar(processo);

        verify(processoMapper).paraDominio(processoEntity);
    }

    @Test
    void deve_retornar_processo_mapeado() {
        when(processoMapper.paraEntidade(processo)).thenReturn(processoEntity);
        when(processoRepository.save(processoEntity)).thenReturn(processoEntity);
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processo);

        Processo processoSalvo = salvadorDeProcesso.salvar(processo);

        assertEquals(processo, processoSalvo);
    }
}