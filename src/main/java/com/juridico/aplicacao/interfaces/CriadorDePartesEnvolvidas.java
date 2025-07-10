package com.juridico.aplicacao.interfaces;

import com.juridico.aplicacao.dto.ParteEnvolvidaParams;
import com.juridico.dominio.model.ParteEnvolvida;

import java.util.List;

public interface CriadorDePartesEnvolvidas {
    List<ParteEnvolvida> criar(ParteEnvolvidaParams acaoParams, Long idProcesso);
}
