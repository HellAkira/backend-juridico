package com.juridico.aplicacao.service.interfaces;

import com.juridico.aplicacao.dto.AcaoParams;
import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.aplicacao.service.CriadorDeAcoesImpl;
import com.juridico.dominio.model.Acao;
import com.juridico.dominio.model.Processo;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriadorDeAcoesImplTest {

    @Mock
    private AtualizadorDeProcessoService atualizadorDeProcessoService;
    @Mock
    private BuscadorDeProcessoService buscadorDeProcessoService;
    @InjectMocks
    private CriadorDeAcoesImpl criadorDeAcoes;
    private Long idProcesso;
    private AcaoParams acaoParams;

    @BeforeEach
    void init() {
        idProcesso = Instancio.of(Long.class).create();
        acaoParams = Instancio.of(AcaoParams.class).create();
    }

    @Test
    void deve_buscar_o_processo() {
        buscadorDeProcessoService.buscarPorId(idProcesso);

        verify(buscadorDeProcessoService).buscarPorId(idProcesso);
    }

    @Test
    void deve_criar_uma_acao_no_processo_buscado() {
        Acao acaoSalva = Instancio.of(Acao.class).create();
        Processo processoBuscado = Mockito.mock(Processo.class);
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        Processo processoSalvo = Instancio.of(Processo.class).set(field(Processo::getAcoes), List.of(acaoSalva)).create();
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        criadorDeAcoes.criar(acaoParams, idProcesso);

        verify(processoBuscado).adicionarAcao(acaoParams.getTipo(), acaoParams.getDescricao());
    }

    @Test
    void deve_atualizar_processo_em_que_a_acao_foi_adicionada() {
        Acao acaoSalva = Instancio.of(Acao.class).create();
        Processo processoBuscado = Instancio.of(Processo.class).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        Processo processoSalvo = Instancio.of(Processo.class).set(field(Processo::getAcoes), List.of(acaoSalva)).create();
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        criadorDeAcoes.criar(acaoParams, idProcesso);

        verify(atualizadorDeProcessoService).atualizar(processoBuscado);
    }

    @Test
    void deve_retornar_acoes_do_processo_salvo() {
        Acao acaoSalva = Instancio.of(Acao.class).create();
        Processo processoSalvo = Instancio.of(Processo.class).set(field(Processo::getAcoes), List.of(acaoSalva)).create();
        Processo processoBuscado = Instancio.of(Processo.class).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        Acao acaoRetornada = criadorDeAcoes.criar(acaoParams, idProcesso).getFirst();

        assertEquals(acaoSalva, acaoRetornada);
    }
}