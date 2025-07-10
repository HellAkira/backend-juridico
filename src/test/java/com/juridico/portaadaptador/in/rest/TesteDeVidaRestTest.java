package com.juridico.portaadaptador.in.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class TesteDeVidaRestTest {

    @InjectMocks
    private TesteDeVidaRest testeDeVida;

    @Test
    void deve_retornar_estou_vivo(){
        String mensagem = testeDeVida.testeDeVida().getBody();

        Assertions.assertEquals("Estou Vivo!", mensagem);
    }

    @Test
    void deve_retornar_200(){
        HttpStatusCode status = testeDeVida.testeDeVida().getStatusCode();

        Assertions.assertEquals(HttpStatusCode.valueOf(200), status);
    }

}