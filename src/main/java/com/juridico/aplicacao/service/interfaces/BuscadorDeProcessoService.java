package com.juridico.aplicacao.service.interfaces;

import com.juridico.dominio.model.Processo;

public interface BuscadorDeProcessoService {
    Processo buscarPorId(Long idProcesso);
}
