package com.juridico.aplicacao.service.interfaces;

import com.juridico.dominio.model.Processo;

public interface SalvadorDeProcesso {
    Processo salvar(Processo processo);
}
