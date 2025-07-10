package com.juridico.portaadaptador.in.rest.excecoes;

import com.juridico.config.excecoes.ExcecaoNaResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ResolvedorDeExcecoes {

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseBody
    public ResponseEntity<ExcecaoNaResposta> resolverNoSuchElementException(NoSuchElementException ex) {
        ExcecaoNaResposta erro = new ExcecaoNaResposta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ExcecaoNaResposta> resolverIllegalArgumentException(IllegalArgumentException ex) {
        ExcecaoNaResposta erro = new ExcecaoNaResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Argumento inválido",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseBody
    public ResponseEntity<ExcecaoNaResposta> resolverIllegalStateException(IllegalStateException ex) {
        ExcecaoNaResposta erro = new ExcecaoNaResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Estado inválido",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExcecaoNaResposta> resolverException(Exception ex) {
        ExcecaoNaResposta erro = new ExcecaoNaResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}