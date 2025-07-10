package com.juridico.config.excecoes;

import com.juridico.portaadaptador.in.rest.excecoes.ResolvedorDeExcecoes;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResolvedorDeExcecoesTest {

    private ResolvedorDeExcecoes resolvedor;

    @BeforeEach
    void setup() {
        resolvedor = new ResolvedorDeExcecoes();
    }

    @Test
    void deve_resolver_excecao_de_falta_de_elemento() {
        String mensagem = Instancio.of(String.class).create();
        NoSuchElementException ex = new NoSuchElementException(mensagem);

        ResponseEntity<ExcecaoNaResposta> response = resolvedor.resolverNoSuchElementException(ex);

        ExcecaoNaResposta body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND.value(), body.getStatus());
        assertEquals("Recurso não encontrado", body.getErro());
        assertEquals(mensagem, body.getMensagem());
        assertNotNull(body.getHorario());
    }

    @Test
    void deve_resolver_excecao_de_argumento_invalido() {
        String mensagem = Instancio.of(String.class).create();
        IllegalArgumentException ex = new IllegalArgumentException(mensagem);

        ResponseEntity<ExcecaoNaResposta> response = resolvedor.resolverIllegalArgumentException(ex);

        ExcecaoNaResposta body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
        assertEquals("Argumento inválido", body.getErro());
        assertEquals(mensagem, body.getMensagem());
        assertNotNull(body.getHorario());
    }

    @Test
    void deve_resolver_excecao_de_estado_invalido() {
        String mensagem = Instancio.of(String.class).create();
        IllegalStateException ex = new IllegalStateException(mensagem);

        ResponseEntity<ExcecaoNaResposta> response = resolvedor.resolverIllegalStateException(ex);

        ExcecaoNaResposta body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
        assertEquals("Estado inválido", body.getErro());
        assertEquals(mensagem, body.getMensagem());
        assertNotNull(body.getHorario());
    }

    @Test
    void deve_resolver_excecao_generica() {
        String mensagem = Instancio.of(String.class).create();
        Exception ex = new Exception(mensagem);

        ResponseEntity<ExcecaoNaResposta> response = resolvedor.resolverException(ex);

        ExcecaoNaResposta body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.getStatus());
        assertEquals("Erro interno", body.getErro());
        assertEquals(mensagem, body.getMensagem());
        assertNotNull(body.getHorario());
    }
}
