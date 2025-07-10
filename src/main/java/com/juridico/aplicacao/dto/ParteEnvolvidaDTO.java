package com.juridico.aplicacao.dto;

import com.juridico.comum.enums.TipoParteEnvolvida;
import lombok.Data;

@Data
public class ParteEnvolvidaDTO {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;
    private TipoParteEnvolvida tipoParteEnvolvida;
}
