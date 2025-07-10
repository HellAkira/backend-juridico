package com.juridico.aplicacao.interfaces;

import com.juridico.aplicacao.dto.AcaoParams;
import com.juridico.dominio.model.Acao;

import java.util.List;

public interface CriadorDeAcoes {
    List<Acao> criar(AcaoParams acaoParams, Long idProcesso);
}
