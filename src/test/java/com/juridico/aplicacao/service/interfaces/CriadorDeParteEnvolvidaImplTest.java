package com.juridico.aplicacao.service.interfaces;

import com.juridico.aplicacao.dto.ParteEnvolvidaParams;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.aplicacao.service.AtualizadorDeProcessoServiceImpl;
import com.juridico.aplicacao.service.CriadorDePartesEnvolvidasImpl;
import com.juridico.dominio.model.ParteEnvolvida;
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
class CriadorDeParteEnvolvidaImplTest {

    @Mock
    private AtualizadorDeProcessoServiceImpl atualizadorDeProcessoService;
    @Mock
    private BuscadorDeProcessoService buscadorDeProcessoService;
    @InjectMocks
    private CriadorDePartesEnvolvidasImpl criadorDePartesEnvolvidas;

    private Long idProcesso;
    private ParteEnvolvidaParams parteEnvolvidaParams;

    @BeforeEach
    void init() {
        idProcesso = Instancio.of(Long.class).create();
        parteEnvolvidaParams = Instancio.of(ParteEnvolvidaParams.class).create();
    }

    @Test
    void deve_buscar_o_processo() {
        buscadorDeProcessoService.buscarPorId(idProcesso);

        verify(buscadorDeProcessoService).buscarPorId(idProcesso);
    }

    @Test
    void deve_criar_uma_parte_envolvida_no_processo_buscado() {
        ParteEnvolvida parteEnvolvidaSalva = Instancio.of(ParteEnvolvida.class).create();
        Processo processoBuscado = Mockito.mock(Processo.class);
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        Processo processoSalvo = Instancio.of(Processo.class).set(field(Processo::getPartesEnvolvidas), List.of(parteEnvolvidaSalva)).create();
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso);

        verify(processoBuscado).adicionarParteEnvolvida(parteEnvolvidaParams.getNome(),
                parteEnvolvidaParams.getCpfCnpj(),
                parteEnvolvidaParams.getTelefone(),
                parteEnvolvidaParams.getEmail(),
                parteEnvolvidaParams.getTipoParteEnvolvida());
    }

    @Test
    void deve_atualizar_processo_em_que_a_parte_envolvida_foi_adicionada() {
        ParteEnvolvida parteEnvolvidaSalva = Instancio.of(ParteEnvolvida.class).create();
        Processo processoBuscado = Instancio.of(Processo.class).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        Processo processoSalvo = Instancio.of(Processo.class).set(field(Processo::getPartesEnvolvidas), List.of(parteEnvolvidaSalva)).create();
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso);

        verify(atualizadorDeProcessoService).atualizar(processoBuscado);
    }

    @Test
    void deve_retornar_partes_envolvidas_do_processo_atualizado() {
        ParteEnvolvida parteEnvolvidaSalva = Instancio.of(ParteEnvolvida.class).create();
        Processo processoSalvo = Instancio.of(Processo.class)
                .set(field(Processo::getPartesEnvolvidas), List.of(parteEnvolvidaSalva)).create();
        Processo processoBuscado = Instancio.of(Processo.class).create();
        when(buscadorDeProcessoService.buscarPorId(idProcesso)).thenReturn(processoBuscado);
        when(atualizadorDeProcessoService.atualizar(processoBuscado)).thenReturn(processoSalvo);

        ParteEnvolvida parteEnvolvidaRetornada = criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso).getFirst();

        assertEquals(parteEnvolvidaSalva, parteEnvolvidaRetornada);
    }
}