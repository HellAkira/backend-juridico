package com.juridico.portaadaptador.rest;

import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.aplicacao.dto.ParteEnvolvidaParams;
import com.juridico.aplicacao.interfaces.CriadorDePartesEnvolvidas;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.portaadaptador.in.rest.GerenciarParteEnvolvidasRest;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GerenciarParteEnvolvidaRestTest {
    @Mock
    private CriadorDePartesEnvolvidas criadorDePartesEnvolvidas;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private GerenciarParteEnvolvidasRest gerenciarParteEnvolvidaRest;
    private ParteEnvolvidaParams parteEnvolvidaParams;
    private ParteEnvolvidaDTO parteEnvolvidaDTO;
    private ParteEnvolvida parteEnvolvida;
    private Long idProcesso;

    @BeforeEach
    void init() {
        idProcesso = Instancio.of(Long.class).create();
        parteEnvolvidaParams = Instancio.of(ParteEnvolvidaParams.class).create();
        parteEnvolvidaDTO = Instancio.of(ParteEnvolvidaDTO.class).create();
        parteEnvolvida = Instancio.of(ParteEnvolvida.class).create();
    }

    @Test
    void deve_chamar_o_criador_de_parte_envolvida() {

        gerenciarParteEnvolvidaRest.criar(parteEnvolvidaParams, idProcesso);

        verify(criadorDePartesEnvolvidas).criar(parteEnvolvidaParams, idProcesso);
    }

    @Test
    void deve_mapear_parte_envolvida_criada() {
        when(criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso)).thenReturn(List.of(parteEnvolvida));

        gerenciarParteEnvolvidaRest.criar(parteEnvolvidaParams, idProcesso);

        verify(modelMapper).map(parteEnvolvida, ParteEnvolvidaDTO.class);
    }

    @Test
    void deve_retornar_parte_envolvida_mapeada() {
        when(criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso)).thenReturn(List.of(parteEnvolvida));
        when(modelMapper.map(parteEnvolvida, ParteEnvolvidaDTO.class)).thenReturn(parteEnvolvidaDTO);

        var parteEnvolvidaDTO = gerenciarParteEnvolvidaRest.criar(parteEnvolvidaParams, idProcesso).getBody();

        Assertions.assertNotNull(parteEnvolvidaDTO);
        ParteEnvolvidaDTO parteEnvolvidaRetornada = parteEnvolvidaDTO.getFirst();
        Assertions.assertEquals(this.parteEnvolvidaDTO.getCpfCnpj(), parteEnvolvidaRetornada.getCpfCnpj());
        Assertions.assertEquals(this.parteEnvolvidaDTO.getNome(), parteEnvolvidaRetornada.getNome());
        Assertions.assertEquals(this.parteEnvolvidaDTO.getEmail(), parteEnvolvidaRetornada.getEmail());
        Assertions.assertEquals(this.parteEnvolvidaDTO.getTelefone(), parteEnvolvidaRetornada.getTelefone());
        Assertions.assertEquals(this.parteEnvolvidaDTO.getTipoParteEnvolvida(), parteEnvolvidaRetornada.getTipoParteEnvolvida());
    }
}