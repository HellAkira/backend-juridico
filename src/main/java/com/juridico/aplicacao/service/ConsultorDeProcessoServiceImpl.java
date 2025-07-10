package com.juridico.aplicacao.service;

import com.juridico.aplicacao.service.interfaces.ConsultorDeProcessoService;
import com.juridico.comum.enums.StatusProcesso;
import com.juridico.dominio.model.Processo;
import com.juridico.comum.mapper.ProcessoMapper;
import com.juridico.portaadaptador.repository.ProcessoRepository;
import com.juridico.portaadaptador.repository.ProcessoSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsultorDeProcessoServiceImpl implements ConsultorDeProcessoService {

    private final ProcessoRepository processoRepository;
    private final ProcessoMapper processoMapper;

    @Override
    public List<Processo> consultarProcesso(String cpfCnpj,
                                            StatusProcesso statusProcesso,
                                            LocalDate dataDeAbertura) {
        var processos = processoRepository.findAll(ProcessoSpecifications.comFiltro(cpfCnpj, statusProcesso, dataDeAbertura));
        if (processos.isEmpty()) {
            throw new NoSuchElementException("Não foi encontrado um processo para parametros");
        }
        return processos.stream().map(processoMapper::paraDominio).toList();
    }
}
