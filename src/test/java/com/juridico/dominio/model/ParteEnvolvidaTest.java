package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.TipoParteEnvolvida;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ParteEnvolvidaTest {

    @Test
    void deve_criar_parte_envolvida_com_todos_os_campos_preenchidos() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = TipoParteEnvolvida.AUTOR;

        var parteEnvolvida = new ParteEnvolvida(nome, cpfCnpj, telefone, email, tipoParteEnvolvida);

        Assertions.assertEquals(nome, parteEnvolvida.getNome());
        Assertions.assertEquals(cpfCnpj, parteEnvolvida.getCpfCnpj());
        Assertions.assertEquals(email, parteEnvolvida.getEmail());
        Assertions.assertEquals(telefone, parteEnvolvida.getTelefone());
        Assertions.assertEquals(tipoParteEnvolvida, parteEnvolvida.getTipoParteEnvolvida());
    }

    @Test
    void deve_retornar_erro_quando_nome_for_nulo() {
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = TipoParteEnvolvida.AUTOR;

        Executable action = () -> new ParteEnvolvida(null, cpfCnpj, telefone, email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_nome_for_vazio() {
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = TipoParteEnvolvida.AUTOR;

        Executable action = () -> new ParteEnvolvida("", cpfCnpj, telefone, email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_cpfCnpj_for_vazio() {
        var nome = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, "", telefone, email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_cpfCnpj_for_nulo() {
        var nome = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, null, telefone, email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_email_for_vazio() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, cpfCnpj, telefone, "", tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_email_for_nulo() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, cpfCnpj, telefone, null, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_telefone_for_vazio() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, cpfCnpj, "", email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_telefone_for_nulo() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var tipoParteEnvolvida = Instancio.of(TipoParteEnvolvida.class).create();

        Executable action = () -> new ParteEnvolvida(nome, cpfCnpj, null, email, tipoParteEnvolvida);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }

    @Test
    void deve_retornar_erro_quando_tipo_for_nulo() {
        var nome = Instancio.of(String.class).create();
        var cpfCnpj = Instancio.of(String.class).create();
        var email = Instancio.of(String.class).create();
        var telefone = Instancio.of(String.class).create();

        Executable action = () -> new ParteEnvolvida(nome, cpfCnpj, telefone, email, null);

        Assertions.assertThrows(IllegalArgumentException.class, action, "Nome não pode ser nulo ou vazio.");
    }
}