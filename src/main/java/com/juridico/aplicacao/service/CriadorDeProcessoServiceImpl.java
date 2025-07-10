package com.juridico.aplicacao.service;

import com.juridico.aplicacao.params.ProcessoParams;
import com.juridico.aplicacao.service.interfaces.CriadorDeProcessoService;
import com.juridico.aplicacao.service.interfaces.SalvadorDeProcesso;
import com.juridico.dominio.model.Processo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class CriadorDeProcessoServiceImpl implements CriadorDeProcessoService {

    private final SalvadorDeProcesso salvadorDeProcesso;

    @Override
    public Processo criarProcesso(ProcessoParams processoParams) {
        Processo processo = new Processo(
                processoParams.getDataDeAbertura(),
                processoParams.getDescricaoDoCaso(),
                processoParams.getStatusProcesso());
        return salvadorDeProcesso.salvar(processo);
    }
}
