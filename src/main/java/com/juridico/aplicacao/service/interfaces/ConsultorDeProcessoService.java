package com.juridico.aplicacao.service.interfaces;

import com.juridico.comum.enums.StatusProcesso;
import com.juridico.dominio.model.Processo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultorDeProcessoService {
    List<Processo> consultarProcesso(String cpfCnpj,
                                     StatusProcesso statusProcesso,
                                     LocalDate dataDeAbertura);
}
