package com.juridico.aplicacao.service;

import com.juridico.aplicacao.dto.ParteEnvolvidaParams;
import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.aplicacao.interfaces.BuscadorDeProcessoService;
import com.juridico.aplicacao.interfaces.CriadorDePartesEnvolvidas;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.dominio.model.Processo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CriadorDePartesEnvolvidasImpl implements CriadorDePartesEnvolvidas {
    private final BuscadorDeProcessoService buscadorDeProcessoService;
    private final AtualizadorDeProcessoService atualizadorDeProcessoService;

    @Override
    public List<ParteEnvolvida> criar(ParteEnvolvidaParams parteEnvolvidaParams, Long idProcesso) {
        Processo processo = buscadorDeProcessoService.buscarPorId(idProcesso);
        processo.adicionarParteEnvolvida(parteEnvolvidaParams.getNome(),
                parteEnvolvidaParams.getCpfCnpj(),
                parteEnvolvidaParams.getTelefone(),
                parteEnvolvidaParams.getEmail(),
                parteEnvolvidaParams.getTipoParteEnvolvida());
        return atualizadorDeProcessoService.atualizar(processo).getPartesEnvolvidas();
    }
}
