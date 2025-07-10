package com.juridico.aplicacao.dto;

import com.juridico.dominio.model.enums.TipoParteEnvolvida;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Parâmetros para partes envolvidas")
public class ParteEnvolvidaParams {
    @Schema(description = "Nome da parte envolvida", example = "João Silva")
    private String nome;
    @Schema(description = "CPF ou CNPJ da parte envolvida", example = "12345678910")
    private String cpfCnpj;
    @Schema(description = "Email da parte envolvida", example = "joao.silva@example.com")
    private String email;
    @Schema(description = "Telefone da parte envolvida", example = "119999999999")
    private String telefone;
    @Schema(description = "Tipo da parte envolvida. [AUTOR, REU, ADVOGADO]", example = "AUTOR")
    private TipoParteEnvolvida tipoParteEnvolvida;
}
