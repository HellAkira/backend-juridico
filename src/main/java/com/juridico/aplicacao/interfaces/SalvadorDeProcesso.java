package com.juridico.aplicacao.interfaces;

import com.juridico.dominio.model.Processo;

public interface SalvadorDeProcesso {
    Processo salvar(Processo processo);
}
