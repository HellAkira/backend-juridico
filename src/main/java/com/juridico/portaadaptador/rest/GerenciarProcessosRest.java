package com.juridico.portaadaptador.rest;

import com.juridico.aplicacao.dto.ProcessoDTO;
import com.juridico.aplicacao.params.ProcessoParams;
import com.juridico.aplicacao.service.interfaces.ArquivadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.ConsultorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.CriadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.EditorDeProcessoService;
import com.juridico.comum.enums.StatusProcesso;
import com.juridico.dominio.model.Processo;
import com.juridico.comum.mapper.ProcessoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/processos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GerenciarProcessosRest {

    private final CriadorDeProcessoService criadorDeProcessoService;
    private final EditorDeProcessoService editadorDeProcessoService;
    private final ConsultorDeProcessoService consultorDeProcessoService;
    private final ArquivadorDeProcessoService arquivadorDeProcessoService;
    private final ProcessoMapper processoMapper;

    @Operation(summary = "Cria um novo processo.")
    @PostMapping("/criar")
    public ResponseEntity<ProcessoDTO> criarProcesso(@RequestBody ProcessoParams processoParams) {
        Processo processo = criadorDeProcessoService.criarProcesso(processoParams);
        return ResponseEntity.ok(processoMapper.paraDTO(processo));
    }

    @Operation(summary = "Edita um processo existente.")
    @PutMapping("/editar")
    public ResponseEntity<ProcessoDTO> editarProcesso(@RequestBody ProcessoParams processoParams,
                                                      @Parameter(description = "ID do processo a ser editado.", example = "123")
                                                      @RequestParam Long idProcesso) {
        Processo processo = editadorDeProcessoService.editarProcesso(processoParams, idProcesso);
        return ResponseEntity.ok(processoMapper.paraDTO(processo));
    }

    @Operation(summary = "Consulta um processo existente.")
    @GetMapping("/consultar")
    public ResponseEntity<List<ProcessoDTO>> consultarProcesso(@Parameter(description = "CPF ou CNPJ do processo.", example = "00000000000")
                                                               @RequestParam(required = false) String cpfCnpj,
                                                               @Parameter(description = "Status do processo. [ATIVO, SUSPENSO ,ARQUIVADO]", example = "ATIVO")
                                                               @RequestParam(required = false) StatusProcesso statusProcesso,
                                                               @Parameter(description = "Data de abertura do processo.", example = "2023-05-15")
                                                               @RequestParam(required = false) LocalDate dataDeAbertura) {
        List<Processo> processos = consultorDeProcessoService.consultarProcesso(cpfCnpj, statusProcesso, dataDeAbertura);
        return ResponseEntity.ok(processos.stream().map(processoMapper::paraDTO).toList());
    }

    @Operation(summary = "Arquiva um processo existente.")
    @PutMapping("/arquivar")
    public ResponseEntity<String> arquivarProcesso(@Parameter(description = "ID do processo a ser arquivado.", example = "123")
                                                   @RequestParam Long idProcesso) {
        arquivadorDeProcessoService.arquivarProcesso(idProcesso);
        return ResponseEntity.ok("Arquivado com sucesso!");
    }
}
