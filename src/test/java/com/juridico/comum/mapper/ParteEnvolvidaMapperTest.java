package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.portaadaptador.mapper.ParteEnvolvidaMapper;
import com.juridico.portaadaptador.out.entity.ParteEnvolvidaEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ParteEnvolvidaMapperTest {

    @InjectMocks
    private ParteEnvolvidaMapper mapper;

    @Test
    void deve_mapear_para_entidade() {
        ParteEnvolvida dominio = Instancio.of(ParteEnvolvida.class).create();

        ParteEnvolvidaEntity entidade = mapper.paraEntidade(dominio);

        assertNotNull(entidade);
        assertEquals(dominio.getId(), entidade.getId());
        assertEquals(dominio.getNome(), entidade.getNome());
        assertEquals(dominio.getCpfCnpj(), entidade.getCpfCnpj());
        assertEquals(dominio.getEmail(), entidade.getEmail());
        assertEquals(dominio.getTelefone(), entidade.getTelefone());
        assertEquals(dominio.getTipoParteEnvolvida(), entidade.getTipoParteEnvolvida());
    }

    @Test
    void deve_mapear_para_dominio() {
        ParteEnvolvidaEntity entidade = Instancio.of(ParteEnvolvidaEntity.class).create();

        ParteEnvolvida dominio = mapper.paraDominio(entidade);

        assertNotNull(dominio);
        assertEquals(entidade.getId(), dominio.getId());
        assertEquals(entidade.getNome(), dominio.getNome());
        assertEquals(entidade.getCpfCnpj(), dominio.getCpfCnpj());
        assertEquals(entidade.getEmail(), dominio.getEmail());
        assertEquals(entidade.getTelefone(), dominio.getTelefone());
        assertEquals(entidade.getTipoParteEnvolvida(), dominio.getTipoParteEnvolvida());
    }

    @Test
    void deve_mapear_para_dto() {
        ParteEnvolvida dominio = Instancio.of(ParteEnvolvida.class).create();

        ParteEnvolvidaDTO dto = mapper.paraDTO(dominio);

        assertNotNull(dto);
        assertEquals(dominio.getId(), dto.getId());
        assertEquals(dominio.getNome(), dto.getNome());
        assertEquals(dominio.getCpfCnpj(), dto.getCpfCnpj());
        assertEquals(dominio.getEmail(), dto.getEmail());
        assertEquals(dominio.getTelefone(), dto.getTelefone());
        assertEquals(dominio.getTipoParteEnvolvida(), dto.getTipoParteEnvolvida());
    }
}
