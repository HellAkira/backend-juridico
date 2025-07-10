package com.juridico.aplicacao.service;

import com.juridico.aplicacao.dto.AcaoParams;
import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.aplicacao.interfaces.CriadorDeAcoes;
import com.juridico.dominio.model.Acao;
import com.juridico.dominio.model.Processo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CriadorDeAcoesImpl implements CriadorDeAcoes {
    private final BuscadorDeProcessoService buscadorDeProcessoService;
    private final AtualizadorDeProcessoService atualizadorDeProcessoService;

    @Override
    public List<Acao> criar(AcaoParams acaoParams, Long idProcesso) {
        Processo processo = buscadorDeProcessoService.buscarPorId(idProcesso);
        processo.adicionarAcao(acaoParams.getTipo(), acaoParams.getDescricao());
        return atualizadorDeProcessoService.atualizar(processo).getAcoes();
    }
}
