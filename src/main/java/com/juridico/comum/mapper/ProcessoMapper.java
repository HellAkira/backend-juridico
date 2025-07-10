package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.ProcessoDTO;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class ProcessoMapper {

    private final AcaoMapper acaoMapper;
    private final ParteEnvolvidaMapper parteEnvolvidaMapper;

    public Processo paraDominio(ProcessoEntity processoEntity) {
        Processo processo = new Processo();
        processo.setId(processoEntity.getId());
        processo.setStatus(processoEntity.getStatus());
        processo.setDescricaoDoCaso(processoEntity.getDescricaoDoCaso());
        processo.setDataDeAbertura(processoEntity.getDataDeAbertura());
        if (processoEntity.getAcoes() != null) {
            processo.setAcoes(processoEntity.getAcoes().stream().map(acaoMapper::paraDominio).toList());
        }
        if (processoEntity.getPartesEnvolvidas() != null) {
            processo.setPartesEnvolvidas(processoEntity.getPartesEnvolvidas().stream().map(parteEnvolvidaMapper::paraDominio).toList());
        }
        return processo;
    }

    public ProcessoDTO paraDTO(Processo processo) {
        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setId(processo.getId());
        processoDTO.setStatusProcesso(processo.getStatus());
        processoDTO.setDescricaoDoCaso(processo.getDescricaoDoCaso());
        processoDTO.setDataDeAbertura(processo.getDataDeAbertura());
        if (processo.getAcoes() != null) {
            processoDTO.setAcoes(processo.getAcoes().stream().map(acaoMapper::paraDTO).toList());
        }
        if (processo.getPartesEnvolvidas() != null) {
            processoDTO.setPartesEnvolvidas(processo.getPartesEnvolvidas().stream().map(parteEnvolvidaMapper::paraDTO).toList());
        }
        return processoDTO;
    }

    public ProcessoEntity paraEntidade(Processo processo) {
        ProcessoEntity processoEntity = new ProcessoEntity();
        processoEntity.setId(processo.getId());
        processoEntity.setStatus(processo.getStatus());
        processoEntity.setDescricaoDoCaso(processo.getDescricaoDoCaso());
        processoEntity.setDataDeAbertura(processo.getDataDeAbertura());
        if (processo.getAcoes() != null) {
            processoEntity.setAcoes(processo.getAcoes().stream().map(acaoMapper::paraEntidade).toList());
        }
        if (processo.getPartesEnvolvidas() != null) {
            processoEntity.setPartesEnvolvidas(processo.getPartesEnvolvidas().stream().map(parteEnvolvidaMapper::paraEntidade).toList());
        }
        return processoEntity;
    }
}
