package com.juridico.aplicacao.service;

import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import com.juridico.portaadaptador.out.repository.ProcessoRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscadorDeProcessoServiceImplTest {

    @Mock
    private ProcessoRepository processoRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BuscadorDeProcessoServiceImpl buscadorDeProcessoService;

    @Test
    void deve_buscar_entidade_do_processo() {
        Long idProcesso = Instancio.of(Long.class).create();
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        when(processoRepository.findById(idProcesso)).thenReturn(Optional.ofNullable(processoEntity));

        buscadorDeProcessoService.buscarPorId(idProcesso);

        verify(processoRepository).findById(idProcesso);
    }

    @Test
    void deve_retornar_erro_caso_nao_encontre_processo() {
        Long idProcesso = Instancio.of(Long.class).create();

        Executable action = () -> buscadorDeProcessoService.buscarPorId(idProcesso);

        assertThrows(NoSuchElementException.class, action, "Processo não encontrado");
    }

    @Test
    void deve_enviar_entidade_para_mapear() {
        Long idProcesso = Instancio.of(Long.class).create();
        Processo processoMapeado = Instancio.of(Processo.class).create();
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        when(modelMapper.map(processoEntity, Processo.class)).thenReturn(processoMapeado);
        when(processoRepository.findById(idProcesso)).thenReturn(Optional.ofNullable(processoEntity));

        buscadorDeProcessoService.buscarPorId(idProcesso);

        verify(modelMapper).map(processoEntity, Processo.class);
    }


    @Test
    void deve_retornar_processo_mapeado() {
        Long idProcesso = Instancio.of(Long.class).create();
        Processo processoMapeado = Instancio.of(Processo.class).create();
        ProcessoEntity processoEntity = Instancio.of(ProcessoEntity.class).create();
        when(modelMapper.map(processoEntity, Processo.class)).thenReturn(processoMapeado);
        when(processoRepository.findById(idProcesso)).thenReturn(Optional.ofNullable(processoEntity));

        Processo processoRetornado = buscadorDeProcessoService.buscarPorId(idProcesso);

        Assertions.assertEquals(processoRetornado, processoMapeado);
    }
}