package com.juridico.aplicacao.service.interfaces;

import com.juridico.aplicacao.params.AcaoParams;
import com.juridico.dominio.model.Acao;

import java.util.List;

public interface CriadorDeAcoes {
    List<Acao> criar(AcaoParams acaoParams, Long idProcesso);
}
