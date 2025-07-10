package com.juridico.aplicacao.interfaces;

import com.juridico.dominio.model.Processo;
import com.juridico.dominio.model.enums.StatusProcesso;

import java.time.LocalDate;
import java.util.List;

public interface ConsultorDeProcessoService {
    List<Processo> consultarProcessos(String cpfCnpj,
                                      StatusProcesso statusProcesso,
                                      LocalDate dataDeAbertura);
}
