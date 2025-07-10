package com.juridico.aplicacao.service;

import com.juridico.aplicacao.service.interfaces.BuscadorDeProcessoService;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.entity.ProcessoEntity;
import com.juridico.portaadaptador.repository.ProcessoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class BuscadorDeProcessoServiceImpl implements BuscadorDeProcessoService {

    private final ProcessoRepository processoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Processo buscarPorId(Long idProcesso) {
        ProcessoEntity processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new NoSuchElementException("Processo não encontrado"));
        return modelMapper.map(processo, Processo.class);
    }
}
