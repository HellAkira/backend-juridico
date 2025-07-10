package com.juridico.aplicacao.dto;

import com.juridico.comum.enums.StatusProcesso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDTO {
    private Long id;
    private LocalDate dataDeAbertura;
    private String descricaoDoCaso;
    private StatusProcesso statusProcesso;
    private List<AcaoDTO> acoes;
    private List<ParteEnvolvidaDTO> partesEnvolvidas;
}
