package com.juridico.aplicacao.service.interfaces;

import com.juridico.aplicacao.params.ProcessoParams;
import com.juridico.dominio.model.Processo;

public interface CriadorDeProcessoService {
    Processo criarProcesso(ProcessoParams processoParams);
}
