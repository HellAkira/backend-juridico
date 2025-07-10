package com.juridico.portaadaptador.rest;

import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.aplicacao.params.ParteEnvolvidaParams;
import com.juridico.aplicacao.service.interfaces.CriadorDePartesEnvolvidas;
import com.juridico.dominio.model.ParteEnvolvida;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parte-envolvida")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GerenciarParteEnvolvidasRest {

    private final CriadorDePartesEnvolvidas criadorDePartesEnvolvidas;
    private final ModelMapper modelMapper;

    @Operation(summary = "Regista uma parte envolvida e vincula ao processo com o ID passado.")
    @PostMapping("/registrar")
    public ResponseEntity<List<ParteEnvolvidaDTO>> criar(@RequestBody ParteEnvolvidaParams parteEnvolvidaParams,
                                                         @RequestParam Long idProcesso) {
        List<ParteEnvolvida> parteEnvolvidas = criadorDePartesEnvolvidas.criar(parteEnvolvidaParams, idProcesso);
        return ResponseEntity.ok(parteEnvolvidas.stream().map(acao -> modelMapper.map(acao, ParteEnvolvidaDTO.class)).toList());
    }
}
