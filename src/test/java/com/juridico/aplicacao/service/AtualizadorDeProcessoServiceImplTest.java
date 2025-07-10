package com.juridico.aplicacao.service;

import com.juridico.dominio.model.Acao;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.mapper.AcaoMapper;
import com.juridico.portaadaptador.mapper.ParteEnvolvidaMapper;
import com.juridico.portaadaptador.mapper.ProcessoMapper;
import com.juridico.portaadaptador.out.entity.AcaoEntity;
import com.juridico.portaadaptador.out.entity.ParteEnvolvidaEntity;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import com.juridico.portaadaptador.out.repository.ProcessoRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizadorDeProcessoServiceImplTest {

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private ProcessoMapper processoMapper;

    @Mock
    private AcaoMapper acaoMapper;

    @Mock
    private ParteEnvolvidaMapper parteEnvolvidaMapper;

    @Captor
    private ArgumentCaptor<ProcessoEntity> argumentCaptor;

    @InjectMocks
    private AtualizadorDeProcessoServiceImpl atualizadorDeProcessoService;

    private Processo processo;
    private ProcessoEntity processoEntity;
    private Acao acao;
    private AcaoEntity acaoEntity;
    private ParteEnvolvida parteEnvolvida;
    private ParteEnvolvidaEntity parteEnvolvidaEntity;

    @BeforeEach
    void setUp() {
        acao = Instancio.of(Acao.class).create();
        acaoEntity = Instancio.of(AcaoEntity.class).create();
        parteEnvolvida = Instancio.of(ParteEnvolvida.class).create();
        parteEnvolvidaEntity = Instancio.of(ParteEnvolvidaEntity.class).create();
        processo = Instancio.of(Processo.class)
                .set(field(Processo::getAcoes), List.of(acao))
                .set(field(Processo::getPartesEnvolvidas), List.of(parteEnvolvida))
                .create();
        processoEntity = Instancio.of(ProcessoEntity.class)
                .set(field(ProcessoEntity::getAcoes), List.of(acaoEntity))
                .set(field(ProcessoEntity::getPartesEnvolvidas), List.of(parteEnvolvidaEntity))
                .create();
    }

    @Test
    void deve_buscar_processo() {
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processoEntity));
        when(acaoMapper.paraEntidade(acao)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteEnvolvida)).thenReturn(parteEnvolvidaEntity);

        atualizadorDeProcessoService.atualizar(processo);

        verify(processoRepository).findById(processo.getId());
    }

    @Test
    void deve_retornar_erro_caso_nao_encontre_processo() {
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.empty());

        Executable executable = () -> atualizadorDeProcessoService.atualizar(processo);

        assertThrows(NoSuchElementException.class, executable, "Processo não encontrado");
    }

    @Test
    void deve_salvar_processo_com_dados_atualizados() {
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processoEntity));
        when(acaoMapper.paraEntidade(acao)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteEnvolvida)).thenReturn(parteEnvolvidaEntity);

        atualizadorDeProcessoService.atualizar(processo);

        verify(processoRepository).save(argumentCaptor.capture());
        ProcessoEntity processoAtualizado = argumentCaptor.getValue();
        assertEquals(processo.getStatus(), processoAtualizado.getStatus());
        assertEquals(processo.getDescricaoDoCaso(), processoAtualizado.getDescricaoDoCaso());
        assertEquals(processo.getDataDeAbertura(), processoAtualizado.getDataDeAbertura());
    }

    @Test
    void deve_salvar_acoes_atualizadas() {
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processoEntity));
        when(acaoMapper.paraEntidade(acao)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteEnvolvida)).thenReturn(parteEnvolvidaEntity);

        atualizadorDeProcessoService.atualizar(processo);

        verify(processoRepository).save(argumentCaptor.capture());
        ProcessoEntity capturado = argumentCaptor.getValue();
        assertEquals(acaoEntity, capturado.getAcoes().getFirst());
    }

    @Test
    void deve_salvar_partes_envolvidas_atualizadas() {
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processoEntity));
        when(acaoMapper.paraEntidade(acao)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteEnvolvida)).thenReturn(parteEnvolvidaEntity);

        atualizadorDeProcessoService.atualizar(processo);

        verify(processoRepository).save(argumentCaptor.capture());
        ProcessoEntity capturado = argumentCaptor.getValue();
        assertEquals(parteEnvolvidaEntity, capturado.getPartesEnvolvidas().getFirst());
    }

    @Test
    void deve_retornar_processo_mapeado() {
        Processo processoMapeado = Instancio.of(Processo.class).create();
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processoEntity));
        when(processoMapper.paraDominio(processoEntity)).thenReturn(processoMapeado);
        when(processoRepository.save(processoEntity)).thenReturn(processoEntity);
        when(acaoMapper.paraEntidade(acao)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteEnvolvida)).thenReturn(parteEnvolvidaEntity);

        Processo processoRetornado = atualizadorDeProcessoService.atualizar(processo);

        assertEquals(processoMapeado, processoRetornado);
    }
}