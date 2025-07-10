package com.juridico.aplicacao.dto;

import com.juridico.comum.enums.TipoAcao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AcaoDTO {
    private Long id;
    private TipoAcao tipo;
    private LocalDateTime dataRegistro;
    private String descricao;
}
