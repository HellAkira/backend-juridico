package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.TipoAcao;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class AcaoTest {

    @Test
    void deve_criar_acao_com_tipo_data_registro_e_descricao_passados() {
        var tipoAcao = Instancio.of(TipoAcao.class).create();
        var descricao = Instancio.of(String.class).create();

        var acao = new Acao(tipoAcao, descricao);

        Assertions.assertEquals(tipoAcao, acao.getTipo());
        Assertions.assertEquals(descricao, acao.getDescricao());
    }

    @Test
    void deve_retornar_erro_caso_nao_possua_tipo_de_acao() {
        var descricao = Instancio.of(String.class).create();

        Executable action = () -> new Acao(null, descricao);

        Assertions.assertThrows(IllegalArgumentException.class, action);
    }

    @Test
    void deve_retornar_erro_caso_nao_possua_descricao() {
        var tipoAcao = Instancio.of(TipoAcao.class).create();

        Executable action = () -> new Acao(tipoAcao, null);

        Assertions.assertThrows(IllegalArgumentException.class, action);
    }

    @Test
    void deve_retornar_erro_caso_descricao_seja_vazia() {
        var tipoAcao = Instancio.of(TipoAcao.class).create();

        Executable action = () -> new Acao(tipoAcao, "");

        Assertions.assertThrows(IllegalArgumentException.class, action);
    }

}