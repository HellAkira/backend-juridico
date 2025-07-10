package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.dominio.model.Acao;
import com.juridico.portaadaptador.entity.AcaoEntity;
import org.springframework.stereotype.Component;

@Component
public class AcaoMapper {
    public AcaoEntity paraEntidade(Acao acao) {
        AcaoEntity acaoEntity = new AcaoEntity();
        acaoEntity.setId(acao.getId());
        acaoEntity.setTipo(acao.getTipo());
        acaoEntity.setDescricao(acao.getDescricao());
        acaoEntity.setDataRegistro(acao.getDataRegistro());
        return acaoEntity;
    }

    public Acao paraDominio(AcaoEntity acao) {
        Acao acaoDominio = new Acao();
        acaoDominio.setId(acao.getId());
        acaoDominio.setTipo(acao.getTipo());
        acaoDominio.setDescricao(acao.getDescricao());
        acaoDominio.setDataRegistro(acao.getDataRegistro());
        return acaoDominio;
    }

    public AcaoDTO paraDTO(Acao acao) {
        AcaoDTO acaoDTO = new AcaoDTO();
        acaoDTO.setId(acao.getId());
        acaoDTO.setTipo(acao.getTipo());
        acaoDTO.setDescricao(acao.getDescricao());
        acaoDTO.setDataRegistro(acao.getDataRegistro());
        return acaoDTO;

    }
}
