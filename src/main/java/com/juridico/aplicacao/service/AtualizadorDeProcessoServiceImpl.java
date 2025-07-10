package com.juridico.aplicacao.service;

import com.juridico.aplicacao.interfaces.AtualizadorDeProcessoService;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.mapper.AcaoMapper;
import com.juridico.portaadaptador.mapper.ParteEnvolvidaMapper;
import com.juridico.portaadaptador.mapper.ProcessoMapper;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import com.juridico.portaadaptador.out.repository.ProcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class AtualizadorDeProcessoServiceImpl implements AtualizadorDeProcessoService {

    private final ProcessoRepository processoRepository;
    private final ProcessoMapper processoMapper;
    private final AcaoMapper acaoMapper;
    private final ParteEnvolvidaMapper parteEnvolvidaMapper;

    @Override
    public Processo atualizar(Processo processo) {
        var processoEntity = processoRepository.findById(processo.getId())
                .orElseThrow(() -> new NoSuchElementException("Processo não encontrado"));
        processoEntity.setStatus(processo.getStatus());
        processoEntity.setDescricaoDoCaso(processo.getDescricaoDoCaso());
        processoEntity.setDataDeAbertura(processo.getDataDeAbertura());
        alterarAcoes(processo, processoEntity);
        alterarPartesEnvolvidas(processo, processoEntity);
        ProcessoEntity processoSalvo = processoRepository.save(processoEntity);
        return processoMapper.paraDominio(processoSalvo);
    }

    private void alterarPartesEnvolvidas(Processo processo, ProcessoEntity processoEntity) {
        processo.getPartesEnvolvidas().forEach(parte -> {
            var parteEntity = parteEnvolvidaMapper.paraEntidade(parte);
            if (processoEntity.getPartesEnvolvidas().stream().noneMatch(p -> Objects.equals(parteEntity.getId(), p.getId()))) {
                processoEntity.getPartesEnvolvidas().add(parteEntity);
            }
        });
    }

    private void alterarAcoes(Processo processo, ProcessoEntity processoEntity) {
        processo.getAcoes().forEach(acao -> {
            var acaoEntity = acaoMapper.paraEntidade(acao);
            if (processoEntity.getAcoes().stream().noneMatch(a -> Objects.equals(acaoEntity.getId(), a.getId()))) {
                processoEntity.getAcoes().add(acaoEntity);
            }
        });
    }
}
