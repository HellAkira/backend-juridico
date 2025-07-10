package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.portaadaptador.entity.ParteEnvolvidaEntity;
import org.springframework.stereotype.Component;

@Component
public class ParteEnvolvidaMapper {
    public ParteEnvolvidaEntity paraEntidade(ParteEnvolvida parte) {
        ParteEnvolvidaEntity parteEntity = new ParteEnvolvidaEntity();
        parteEntity.setId(parte.getId());
        parteEntity.setNome(parte.getNome());
        parteEntity.setCpfCnpj(parte.getCpfCnpj());
        parteEntity.setEmail(parte.getEmail());
        parteEntity.setTelefone(parte.getTelefone());
        parteEntity.setTipoParteEnvolvida(parte.getTipoParteEnvolvida());
        return parteEntity;
    }

    public ParteEnvolvida paraDominio(ParteEnvolvidaEntity parteEnvolvidaEntity) {
        ParteEnvolvida parteEnvolvida = new ParteEnvolvida();
        parteEnvolvida.setId(parteEnvolvidaEntity.getId());
        parteEnvolvida.setNome(parteEnvolvidaEntity.getNome());
        parteEnvolvida.setCpfCnpj(parteEnvolvidaEntity.getCpfCnpj());
        parteEnvolvida.setEmail(parteEnvolvidaEntity.getEmail());
        parteEnvolvida.setTelefone(parteEnvolvidaEntity.getTelefone());
        parteEnvolvida.setTipoParteEnvolvida(parteEnvolvidaEntity.getTipoParteEnvolvida());
        return parteEnvolvida;
    }

    public ParteEnvolvidaDTO paraDTO(ParteEnvolvida parteEnvolvida) {
        ParteEnvolvidaDTO parteEnvolvidaDTO = new ParteEnvolvidaDTO();
        parteEnvolvidaDTO.setId(parteEnvolvida.getId());
        parteEnvolvidaDTO.setNome(parteEnvolvida.getNome());
        parteEnvolvidaDTO.setCpfCnpj(parteEnvolvida.getCpfCnpj());
        parteEnvolvidaDTO.setEmail(parteEnvolvida.getEmail());
        parteEnvolvidaDTO.setTelefone(parteEnvolvida.getTelefone());
        parteEnvolvidaDTO.setTipoParteEnvolvida(parteEnvolvida.getTipoParteEnvolvida());
        return parteEnvolvidaDTO;
    }
}
