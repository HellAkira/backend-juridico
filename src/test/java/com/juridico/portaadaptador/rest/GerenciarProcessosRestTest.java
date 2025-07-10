package com.juridico.portaadaptador.rest;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.aplicacao.dto.ProcessoDTO;
import com.juridico.aplicacao.dto.ProcessoParams;
import com.juridico.aplicacao.interfaces.ArquivadorDeProcessoService;
import com.juridico.aplicacao.interfaces.ConsultorDeProcessoService;
import com.juridico.aplicacao.interfaces.CriadorDeProcessoService;
import com.juridico.aplicacao.interfaces.EditorDeProcessoService;
import com.juridico.dominio.model.Processo;
import com.juridico.dominio.model.enums.StatusProcesso;
import com.juridico.portaadaptador.in.rest.GerenciarProcessosRest;
import com.juridico.portaadaptador.mapper.ProcessoMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GerenciarProcessosRestTest {

    @Mock
    private CriadorDeProcessoService criadorDeProcessoService;

    @Mock
    private EditorDeProcessoService editorDeProcessoService;

    @Mock
    private ConsultorDeProcessoService consultorDeProcessoService;

    @Mock
    private ProcessoMapper processoMapper;

    @Mock
    private ArquivadorDeProcessoService arquivadorDeProcessoService;

    @InjectMocks
    private GerenciarProcessosRest gerenciarProcessosRest;
    private ProcessoParams processoParams;
    private ProcessoDTO processoDTO;
    private Long idProcesso;
    private String cpfCnpj;
    private StatusProcesso status;
    private LocalDate dataDeAbertura;

    @BeforeEach
    void setUp() {
        List<ParteEnvolvidaDTO> partesEnvolvidas = Instancio.ofList(ParteEnvolvidaDTO.class).create();
        List<AcaoDTO> acoes = Instancio.ofList(AcaoDTO.class).create();
        String descricaoDoCaso = Instancio.of(String.class).create();
        dataDeAbertura = Instancio.of(LocalDate.class).create();
        idProcesso = Instancio.of(Long.class).create();
        cpfCnpj = Instancio.of(String.class).create();
        status = Instancio.of(StatusProcesso.class).create();
        processoDTO = new ProcessoDTO(idProcesso, dataDeAbertura, descricaoDoCaso, status, acoes, partesEnvolvidas);
        processoParams = new ProcessoParams(dataDeAbertura, descricaoDoCaso, status);
    }

    @Test
    void deve_chamar_o_criador_de_processos() {

        gerenciarProcessosRest.criarProcesso(processoParams);

        Mockito.verify(criadorDeProcessoService).criarProcesso(processoParams);
    }

    @Test
    void deve_retornar_dto_do_processo_quando_criar_processo_com_sucesso() {
        var processo = Instancio.of(Processo.class).create();
        when(processoMapper.paraDTO(processo)).thenReturn(processoDTO);
        when(criadorDeProcessoService.criarProcesso(processoParams)).thenReturn(processo);

        var processoRetornado = gerenciarProcessosRest.criarProcesso(processoParams).getBody();

        Assertions.assertNotNull(processoRetornado);
        Assertions.assertEquals(processoParams.getDataDeAbertura(), processoRetornado.getDataDeAbertura());
        Assertions.assertEquals(processoParams.getDescricaoDoCaso(), processoRetornado.getDescricaoDoCaso());
    }

    @Test
    void deve_retornar_OK_quando_criar_processo_com_sucesso() {
        Processo processoEsperado = Instancio.of(Processo.class).create();
        when(criadorDeProcessoService.criarProcesso(processoParams)).thenReturn(processoEsperado);

        HttpStatusCode statusCodeRetornado = gerenciarProcessosRest.criarProcesso(processoParams).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, statusCodeRetornado);
    }

    @Test
    void deve_chamar_o_editor_de_processos() {

        gerenciarProcessosRest.editarProcesso(processoParams, idProcesso);

        Mockito.verify(editorDeProcessoService).editarProcesso(processoParams, idProcesso);
    }

    @Test
    void deve_retornar_processo_quando_editar_processo_com_sucesso() {
        Processo processo = Instancio.of(Processo.class).create();
        when(processoMapper.paraDTO(processo)).thenReturn(processoDTO);
        when(editorDeProcessoService.editarProcesso(processoParams, idProcesso)).thenReturn(processo);

        var processoRetornado = gerenciarProcessosRest.editarProcesso(processoParams, idProcesso).getBody();

        Assertions.assertNotNull(processoRetornado);
        Assertions.assertEquals(processoParams.getDataDeAbertura(), processoRetornado.getDataDeAbertura());
        Assertions.assertEquals(processoParams.getDescricaoDoCaso(), processoRetornado.getDescricaoDoCaso());
    }

    @Test
    void deve_retornar_OK_quando_editar_processo_com_sucesso() {
        var processoEsperado = Instancio.of(Processo.class).create();
        when(editorDeProcessoService.editarProcesso(processoParams, idProcesso)).thenReturn(processoEsperado);

        var statusCodeRetornado = gerenciarProcessosRest.editarProcesso(processoParams, idProcesso).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, statusCodeRetornado);
    }

    @Test
    void deve_chamar_o_consultor_de_processos() {

        gerenciarProcessosRest.consultarProcesso(cpfCnpj, status, dataDeAbertura);

        Mockito.verify(consultorDeProcessoService).consultarProcesso(cpfCnpj, status, dataDeAbertura);
    }

    @Test
    void deve_retornar_processo_quando_encontrar_processo_na_consulta() {
        var processo = Instancio.of(Processo.class).create();
        when(processoMapper.paraDTO(processo)).thenReturn(processoDTO);
        when(consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura))
                .thenReturn(Collections.singletonList(processo));

        var processoRetornado = gerenciarProcessosRest.consultarProcesso(cpfCnpj, status, dataDeAbertura).getBody();

        Assertions.assertNotNull(processoRetornado);
    }

    @Test
    void deve_retornar_OK_quando_encontrar_processo_na_consulta() {
        var processoEsperado = Instancio.of(Processo.class).create();
        when(consultorDeProcessoService.consultarProcesso(cpfCnpj, status, dataDeAbertura))
                .thenReturn(Collections.singletonList(processoEsperado));

        var statusCodeRetornado = gerenciarProcessosRest.consultarProcesso(cpfCnpj, status, dataDeAbertura).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, statusCodeRetornado);
    }

    @Test
    void deve_chamar_o_arquivador_de_processos() {

        gerenciarProcessosRest.arquivarProcesso(idProcesso);

        Mockito.verify(arquivadorDeProcessoService).arquivarProcesso(idProcesso);
    }

    @Test
    void deve_retornar_mensagem_de_sucesso_quando_nao_houver_erros_ao_arquivar() {

        var mensagemRetorno = gerenciarProcessosRest.arquivarProcesso(idProcesso).getBody();

        Assertions.assertEquals("Arquivado com sucesso!", mensagemRetorno);
    }

    @Test
    void deve_retornar_OK_quando_nao_houver_erros_ao_arquivar() {

        var statusCodeRetornado = gerenciarProcessosRest.arquivarProcesso(idProcesso).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, statusCodeRetornado);
    }
}