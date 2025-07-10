package com.juridico.aplicacao.dto;

import com.juridico.dominio.model.enums.TipoAcao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Parâmetros para ações")
public class AcaoParams {
    @Schema(description = "Tipo da ação. [PETICAO, AUDIENCIA, SENTENCA]", example = "PETICAO")
    private TipoAcao tipo;
    @Schema(description = "Descrição da ação", example = "Petição para o caso...")
    private String descricao;
}
