package com.juridico.aplicacao.service;

import com.juridico.aplicacao.params.ProcessoParams;
import com.juridico.aplicacao.service.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.BuscadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.EditorDeProcessoService;
import com.juridico.dominio.model.Processo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditorDeProcessoServiceImpl implements EditorDeProcessoService {

    private final BuscadorDeProcessoService buscadorDeProcessoService;
    private final AtualizadorDeProcessoService atualizadorDeProcessoService;

    @Override
    public Processo editarProcesso(ProcessoParams processoParams, Long idProcesso) {
        var processo = buscadorDeProcessoService.buscarPorId(idProcesso);
        processo.setDataDeAbertura(processoParams.getDataDeAbertura());
        processo.setDescricaoDoCaso(processoParams.getDescricaoDoCaso());
        processo.setStatus(processoParams.getStatusProcesso());
        return atualizadorDeProcessoService.atualizar(processo);
    }
}
