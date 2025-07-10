package com.juridico.aplicacao.interfaces;

import com.juridico.aplicacao.dto.ProcessoParams;
import com.juridico.dominio.model.Processo;

public interface CriadorDeProcessoService {
    Processo criarProcesso(ProcessoParams processoParams);
}
