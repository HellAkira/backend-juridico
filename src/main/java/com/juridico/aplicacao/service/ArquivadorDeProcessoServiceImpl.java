package com.juridico.aplicacao.service;

import com.juridico.aplicacao.interfaces.ArquivadorDeProcessoService;
import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.dominio.model.Processo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArquivadorDeProcessoServiceImpl implements ArquivadorDeProcessoService {

    private final BuscadorDeProcessoService buscadorDeProcessoService;
    private final AtualizadorDeProcessoService atualizadorDeProcessoService;

    @Override
    public void arquivarProcesso(Long idProcesso) {
        Processo processo = buscadorDeProcessoService.buscarPorId(idProcesso);
        processo.arquivar();
        atualizadorDeProcessoService.atualizar(processo);
    }
}
