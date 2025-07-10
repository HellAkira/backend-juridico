package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.dominio.model.Acao;
import com.juridico.portaadaptador.entity.AcaoEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions .*;

@ExtendWith(MockitoExtension.class)
class AcaoMapperTest {

    @InjectMocks
    private AcaoMapper mapper;

    @Test
    void deve_mapear_para_entidade() {
        Acao dominio = Instancio.of(Acao.class).create();

        AcaoEntity entidade = mapper.paraEntidade(dominio);

        assertNotNull(entidade);
        assertEquals(dominio.getId(), entidade.getId());
        assertEquals(dominio.getTipo(), entidade.getTipo());
        assertEquals(dominio.getDescricao(), entidade.getDescricao());
        assertEquals(dominio.getDataRegistro(), entidade.getDataRegistro());
    }

    @Test
    void deve_mapear_para_dominio() {
        AcaoEntity entidade = Instancio.of(AcaoEntity.class).create();

        Acao dominio = mapper.paraDominio(entidade);

        assertNotNull(dominio);
        assertEquals(entidade.getId(), dominio.getId());
        assertEquals(entidade.getTipo(), dominio.getTipo());
        assertEquals(entidade.getDescricao(), dominio.getDescricao());
        assertEquals(entidade.getDataRegistro(), dominio.getDataRegistro());
    }

    @Test
    void deve_mapear_para_dto() {
        Acao dominio = Instancio.of(Acao.class).create();

        AcaoDTO dto = mapper.paraDTO(dominio);

        assertNotNull(dto);
        assertEquals(dominio.getId(), dto.getId());
        assertEquals(dominio.getTipo(), dto.getTipo());
        assertEquals(dominio.getDescricao(), dto.getDescricao());
        assertEquals(dominio.getDataRegistro(), dto.getDataRegistro());
    }
}
