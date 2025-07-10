package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.TipoAcao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Acao {
    private Long id;
    private TipoAcao tipo;
    private LocalDateTime dataRegistro;
    private String descricao;

    public Acao(TipoAcao tipoAcao, String descricao) {
        if (tipoAcao == null) {
            throw new IllegalArgumentException("Tipo de ação não pode ser nulo.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }
        this.tipo = tipoAcao;
        this.dataRegistro = LocalDateTime.now();
        this.descricao = descricao;
    }
}
