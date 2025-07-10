package com.juridico.portaadaptador.rest;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.aplicacao.dto.AcaoParams;
import com.juridico.aplicacao.interfaces.CriadorDeAcoes;
import com.juridico.dominio.model.Acao;
import com.juridico.portaadaptador.in.rest.GerenciarAcoesRest;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GerenciarAcoesRestTest {
    @Mock
    private CriadorDeAcoes criadorDeAcoes;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private GerenciarAcoesRest gerenciarAcoesRest;
    private AcaoParams acaoParams;
    private AcaoDTO acaoDTO;
    private Acao acao;
    private Long idProcesso;

    @BeforeEach
    void init() {
        idProcesso = Instancio.of(Long.class).create();
        acaoParams = Instancio.of(AcaoParams.class).create();
        acaoDTO = Instancio.of(AcaoDTO.class).create();
        acao = Instancio.of(Acao.class).create();
    }

    @Test
    void deve_chamar_o_criador_de_acoes() {

        gerenciarAcoesRest.criar(acaoParams, idProcesso);

        verify(criadorDeAcoes).criar(acaoParams, idProcesso);
    }

    @Test
    void deve_mapear_acao_criada() {
        when(criadorDeAcoes.criar(acaoParams, idProcesso)).thenReturn(Collections.singletonList(acao));

        gerenciarAcoesRest.criar(acaoParams, idProcesso);

        verify(modelMapper).map(acao, AcaoDTO.class);
    }

    @Test
    void deve_retornar_acao_mapeada() {
        when(criadorDeAcoes.criar(acaoParams, idProcesso)).thenReturn(Collections.singletonList(acao));
        when(modelMapper.map(acao, AcaoDTO.class)).thenReturn(acaoDTO);

        List<AcaoDTO> acoesRetornadas = gerenciarAcoesRest.criar(acaoParams, idProcesso).getBody();

        Assertions.assertNotNull(acoesRetornadas);
        AcaoDTO acaoRetornada = acoesRetornadas.stream().findFirst().orElseThrow();
        Assertions.assertEquals(acaoDTO.getDescricao(), acaoRetornada.getDescricao());
        Assertions.assertEquals(acaoDTO.getTipo(), acaoRetornada.getTipo());
    }
}