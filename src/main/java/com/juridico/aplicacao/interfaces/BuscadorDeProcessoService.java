package com.juridico.aplicacao.interfaces;

import com.juridico.dominio.model.Processo;

public interface BuscadorDeProcessoService {
    Processo buscarPorId(Long idProcesso);
}
