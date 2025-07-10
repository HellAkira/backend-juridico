package com.juridico.portaadaptador.in.rest.excecoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExcecaoNaResposta {
    private LocalDateTime horario;
    private int status;
    private String erro;
    private String mensagem;
}
