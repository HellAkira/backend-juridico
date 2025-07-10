package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.StatusProcesso;
import com.juridico.dominio.model.enums.TipoAcao;
import com.juridico.dominio.model.enums.TipoParteEnvolvida;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

class ProcessoTest {

    private LocalDate dataDeAbertura;
    private String descricaoDoCaso;
    private StatusProcesso statusProcesso;
    private String descricaoDaAcao;
    private TipoAcao tipo;

    @BeforeEach
    void setUp() {
        dataDeAbertura = LocalDate.now();
        descricaoDoCaso = Instancio.of(String.class).create();
        statusProcesso = Instancio.of(StatusProcesso.class).create();

        tipo = Instancio.of(TipoAcao.class).create();
        descricaoDaAcao = Instancio.of(String.class).create();
    }

    @Test
    void deve_criar_processo_com_data_de_abertura_e_descricao_passada() {

        var processo = new Processo(dataDeAbertura, descricaoDoCaso, statusProcesso);

        Assertions.assertEquals(dataDeAbertura, processo.getDataDeAbertura());
        Assertions.assertEquals(descricaoDoCaso, processo.getDescricaoDoCaso());
    }

    @Test
    void deve_retornar_erro_caso_nao_possua_data_de_abertura() {

        Executable action = () -> new Processo(null, descricaoDoCaso, statusProcesso);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Data de abertura não pode ser nula.");
    }

    @Test
    void deve_retornar_erro_caso_nao_possua_descricao_do_caso() {

        Executable action = () -> new Processo(dataDeAbertura, null, statusProcesso);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Descrição do caso não pode ser nula ou vazia.");
    }

    @Test
    void deve_retornar_erro_caso_descricao_do_caso_seja_vazia() {

        Executable action = () -> new Processo(dataDeAbertura, "", statusProcesso);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Descrição do caso não pode ser nula ou vazia.");
    }

    @Test
    void deve_retornar_erro_caso_nao_possua_status() {

        Executable action = () -> new Processo(dataDeAbertura, descricaoDoCaso, null);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Status do processo não pode ser nulo.");
    }

    @Test
    void deve_alterar_situacao_para_arquivado() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ATIVO);

        processo.arquivar();

        Assertions.assertEquals(StatusProcesso.ARQUIVADO, processo.getStatus());
    }

    @Test
    void deve_retornar_erro_caso_processo_ja_esteja_arquivado() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ARQUIVADO);

        Executable action = processo::arquivar;

        Assertions.assertThrows(IllegalStateException.class, action, "Processo já está arquivado.");
    }

    @Test
    void deve_instanciar_acao_caso_seja_nula() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ATIVO);
        processo.setAcoes(null);

        processo.adicionarAcao(tipo, descricaoDaAcao);

        Assertions.assertNotNull(processo.getAcoes());
    }

    @Test
    void deve_adicionar_acao_ao_processo() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ATIVO);

        processo.adicionarAcao(tipo, descricaoDaAcao);

        Acao acaoAdicionada = processo.getAcoes().getFirst();
        Assertions.assertEquals(tipo, acaoAdicionada.getTipo());
        Assertions.assertEquals(descricaoDaAcao, acaoAdicionada.getDescricao());
    }

    @Test
    void deve_instanciar_parte_envolvida_caso_seja_nula() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ATIVO);
        processo.setAcoes(null);
        String nome = Instancio.of(String.class).create();
        String cpfCnpj = Instancio.of(String.class).create();
        String telefone = Instancio.of(String.class).create();
        String email = Instancio.of(String.class).create();
        TipoParteEnvolvida tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        processo.adicionarParteEnvolvida(nome, cpfCnpj, telefone, email, tipoParteEnvolvida);

        Assertions.assertNotNull(processo.getPartesEnvolvidas());
    }

    @Test
    void deve_adicionar_parte_envolvida_ao_processo() {
        var processo = new Processo(dataDeAbertura, descricaoDoCaso, StatusProcesso.ATIVO);
        String nome = Instancio.of(String.class).create();
        String cpfCnpj = Instancio.of(String.class).create();
        String telefone = Instancio.of(String.class).create();
        String email = Instancio.of(String.class).create();
        TipoParteEnvolvida tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        processo.adicionarParteEnvolvida(nome, cpfCnpj, telefone, email, tipoParteEnvolvida);

        ParteEnvolvida acaoAdicionada = processo.getPartesEnvolvidas().getFirst();
        Assertions.assertEquals(nome, acaoAdicionada.getNome());
        Assertions.assertEquals(cpfCnpj, acaoAdicionada.getCpfCnpj());
        Assertions.assertEquals(telefone, acaoAdicionada.getTelefone());
        Assertions.assertEquals(email, acaoAdicionada.getEmail());
        Assertions.assertEquals(tipoParteEnvolvida, acaoAdicionada.getTipoParteEnvolvida());
    }
}