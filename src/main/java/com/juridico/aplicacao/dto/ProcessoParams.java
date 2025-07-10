package com.juridico.aplicacao.dto;

import com.juridico.dominio.model.enums.StatusProcesso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Parâmetros para um processo")
public class ProcessoParams {
    @Schema(description = "Data de abertura do processo", example = "2023-05-15T10:30:00")
    private LocalDate dataDeAbertura;
    @Schema(description = "Descrição detalhada do caso", example = "O objetivo deste processo é garantir...")
    private String descricaoDoCaso;
    @Schema(description = "Status do processo. [ATIVO, SUSPENSO ,ARQUIVADO]", example = "ATIVO")
    private StatusProcesso statusProcesso;
}
