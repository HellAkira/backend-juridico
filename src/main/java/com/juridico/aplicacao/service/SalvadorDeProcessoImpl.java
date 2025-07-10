package com.juridico.aplicacao.service;

import com.juridico.aplicacao.interfaces.SalvadorDeProcesso;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.mapper.ProcessoMapper;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import com.juridico.portaadaptador.out.repository.ProcessoRepository;
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
