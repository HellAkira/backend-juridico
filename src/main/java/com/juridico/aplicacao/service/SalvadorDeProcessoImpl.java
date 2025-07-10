package com.juridico.aplicacao.service;

import com.juridico.aplicacao.service.interfaces.SalvadorDeProcesso;
import com.juridico.comum.mapper.ProcessoMapper;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import com.juridico.portaadaptador.repository.ProcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SalvadorDeProcessoImpl implements SalvadorDeProcesso {

    private final ProcessoRepository processoRepository;
    private final ProcessoMapper processoMapper;

    @Override
    public Processo salvar(Processo processo) {
        ProcessoEntity processoEntity = processoMapper.paraEntidade(processo);
        ProcessoEntity processoSalvo = processoRepository.save(processoEntity);
        return processoMapper.paraDominio(processoSalvo);
    }
}
