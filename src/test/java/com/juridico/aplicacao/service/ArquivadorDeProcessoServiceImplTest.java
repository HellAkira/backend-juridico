package com.juridico.aplicacao.service;

import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.dominio.model.Processo;
import com.juridico.dominio.model.enums.StatusProcesso;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.instancio.Select.field;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ArquivadorDeProcessoServiceImplTest {

    @Mock
    private BuscadorDeProcessoService buscadorDeProcessoService;
    @Mock
    private AtualizadorDeProcessoService atualizadorDeProcessoService;

    @InjectMocks
    private ArquivadorDeProcessoServiceImpl arquivadorDeProcessoService;

    @Test
    void deve_buscar_o_processo() {
        Long idProcesso = Instancio.of(Long.class).create();
        Processo processo = Instancio.of(Processo.class).set(field(Processo::getStatus), StatusProcesso.ATIVO).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processo);

        arquivadorDeProcessoService.arquivarProcesso(idProcesso);

        verify(buscadorDeProcessoService).buscarPorId(idProcesso);
    }

    @Test
    void deve_arquivar_o_processo() {
        Long idProcesso = Instancio.of(Long.class).create();
        Processo processo = Mockito.mock(Processo.class);
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processo);

        arquivadorDeProcessoService.arquivarProcesso(idProcesso);

        verify(processo).arquivar();
    }

    @Test
    void deve_salvar_o_processo_arquivado() {
        Long idProcesso = Instancio.of(Long.class).create();
        Processo processo = Instancio.of(Processo.class).set(field(Processo::getStatus), StatusProcesso.ATIVO).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processo);

        arquivadorDeProcessoService.arquivarProcesso(idProcesso);

        verify(atualizadorDeProcessoService).atualizar(processo);
    }
}