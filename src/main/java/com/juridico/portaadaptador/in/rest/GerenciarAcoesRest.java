package com.juridico.portaadaptador.in.rest;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.aplicacao.dto.AcaoParams;
import com.juridico.aplicacao.interfaces.CriadorDeAcoes;
import com.juridico.dominio.model.Acao;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GerenciarAcoesRest {

    private final CriadorDeAcoes criadorDeAcoes;
    private final ModelMapper modelMapper;

    @Operation(summary = "Regista uma ação e vincula ao processo com o ID passado.")
    @PostMapping("/registrar")
    public ResponseEntity<List<AcaoDTO>> criar(@RequestBody AcaoParams acaoParams,
                                               @RequestParam Long idProcesso) {
        List<Acao> acoes = criadorDeAcoes.criar(acaoParams, idProcesso);
        return ResponseEntity.ok(acoes.stream().map(acao -> modelMapper.map(acao, AcaoDTO.class)).toList());
    }
}
