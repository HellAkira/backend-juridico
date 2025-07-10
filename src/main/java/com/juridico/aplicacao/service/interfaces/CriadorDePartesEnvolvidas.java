package com.juridico.aplicacao.service.interfaces;

import com.juridico.aplicacao.params.ParteEnvolvidaParams;
import com.juridico.dominio.model.ParteEnvolvida;

import java.util.List;

public interface CriadorDePartesEnvolvidas {
    List<ParteEnvolvida> criar(ParteEnvolvidaParams acaoParams, Long idProcesso);
}
