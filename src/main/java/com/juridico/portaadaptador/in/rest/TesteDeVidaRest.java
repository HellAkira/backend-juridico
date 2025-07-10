package com.juridico.portaadaptador.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TesteDeVidaRest {

    @Operation(summary = "Teste de Vida.")
    @GetMapping()
    public ResponseEntity<String> testeDeVida() {
        return ResponseEntity.ok("Estou Vivo!");
    }
}
