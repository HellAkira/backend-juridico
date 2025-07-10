package com.juridico.aplicacao.service;

import com.juridico.aplicacao.params.ProcessoParams;
import com.juridico.aplicacao.service.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.BuscadorDeProcessoService;
import com.juridico.dominio.model.Processo;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditorDeProcessoServiceImplTest {

    @Mock
    private AtualizadorDeProcessoService atualizadorDeProcessoService;

    @Mock
    private BuscadorDeProcessoService buscadorDeProcessoService;

    @Captor
    private ArgumentCaptor<Processo> argumentCaptor;

    @InjectMocks
    private EditorDeProcessoServiceImpl editorDeProcessoService;
    private Long id;
    private Processo processo;

    @BeforeEach
    void init(){
        id = Instancio.of(Long.class).create();
        processo = Instancio.of(Processo.class).create();
    }

    @Test
    void deve_buscar_o_processo_a_ser_editado() {
        ProcessoParams processoDTO = Instancio.of(ProcessoParams.class).create();
        when(buscadorDeProcessoService.buscarPorId(id)).thenReturn(processo);

        editorDeProcessoService.editarProcesso(processoDTO, id);

        verify(buscadorDeProcessoService).buscarPorId(id);
    }

    @Test
    void deve_salvar_o_processo_com_os_dados_editados() {
        ProcessoParams processoDTO = Instancio.of(ProcessoParams.class).create();
        when(buscadorDeProcessoService.buscarPorId(id)).thenReturn(processo);

        editorDeProcessoService.editarProcesso(processoDTO, id);

        verify(atualizadorDeProcessoService).atualizar(argumentCaptor.capture());
        Processo processoSalvo = argumentCaptor.getValue();
        Assertions.assertEquals(processoSalvo.getDescricaoDoCaso(), processoDTO.getDescricaoDoCaso());
        Assertions.assertEquals(processoSalvo.getDataDeAbertura(), processoDTO.getDataDeAbertura());
        Assertions.assertEquals(processoSalvo.getStatus(), processoDTO.getStatusProcesso());
    }

    @Test
    void deve_retornar_processo_salvo(){
        ProcessoParams processoDTO = Instancio.of(ProcessoParams.class).create();
        when(buscadorDeProcessoService.buscarPorId(id)).thenReturn(processo);
        when(atualizadorDeProcessoService.atualizar(processo)).thenReturn(processo);

        var processoRetornado = editorDeProcessoService.editarProcesso(processoDTO, id);

        Assertions.assertEquals(processo.getId(), processoRetornado.getId());
        Assertions.assertEquals(processo.getDataDeAbertura(), processoRetornado.getDataDeAbertura());
        Assertions.assertEquals(processo.getDescricaoDoCaso(), processoRetornado.getDescricaoDoCaso());

    }
}