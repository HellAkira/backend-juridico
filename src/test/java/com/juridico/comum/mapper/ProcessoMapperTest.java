package com.juridico.comum.mapper;

import com.juridico.aplicacao.dto.AcaoDTO;
import com.juridico.aplicacao.dto.ParteEnvolvidaDTO;
import com.juridico.aplicacao.dto.ProcessoDTO;
import com.juridico.dominio.model.Acao;
import com.juridico.dominio.model.ParteEnvolvida;
import com.juridico.dominio.model.Processo;
import com.juridico.portaadaptador.mapper.AcaoMapper;
import com.juridico.portaadaptador.mapper.ParteEnvolvidaMapper;
import com.juridico.portaadaptador.mapper.ProcessoMapper;
import com.juridico.portaadaptador.out.entity.AcaoEntity;
import com.juridico.portaadaptador.out.entity.ParteEnvolvidaEntity;
import com.juridico.portaadaptador.out.entity.ProcessoEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessoMapperTest {

    @Mock
    private AcaoMapper acaoMapper;

    @Mock
    private ParteEnvolvidaMapper parteEnvolvidaMapper;

    @InjectMocks
    private ProcessoMapper processoMapper;

    private ProcessoEntity entidade;
    private AcaoEntity acaoEntity;
    private ParteEnvolvidaEntity parteEntity;
    private Acao acaoDominio;
    private ParteEnvolvida parteDominio;
    private Processo processo;
    private AcaoDTO acaoDTO;
    private ParteEnvolvidaDTO parteDTO;

    @BeforeEach
    void init() {
        entidade = Instancio.of(ProcessoEntity.class).create();
        acaoEntity = Instancio.of(AcaoEntity.class).create();
        parteEntity = Instancio.of(ParteEnvolvidaEntity.class).create();
        entidade.setAcoes(List.of(acaoEntity));
        entidade.setPartesEnvolvidas(List.of(parteEntity));
        acaoDominio = Instancio.of(Acao.class).create();
        parteDominio = Instancio.of(ParteEnvolvida.class).create();
        processo = Instancio.of(Processo.class).create();
        processo.setAcoes(List.of(acaoDominio));
        processo.setPartesEnvolvidas(List.of(parteDominio));
        acaoDTO = Instancio.of(AcaoDTO.class).create();
        parteDTO = Instancio.of(ParteEnvolvidaDTO.class).create();
    }

    @Test
    void deve_mapear_para_dominio() {
        when(acaoMapper.paraDominio(acaoEntity)).thenReturn(acaoDominio);
        when(parteEnvolvidaMapper.paraDominio(parteEntity)).thenReturn(parteDominio);

        Processo dominioMapeado = processoMapper.paraDominio(entidade);

        assertNotNull(dominioMapeado);
        assertEquals(entidade.getId(), dominioMapeado.getId());
        assertEquals(entidade.getStatus(), dominioMapeado.getStatus());
        assertEquals(entidade.getDescricaoDoCaso(), dominioMapeado.getDescricaoDoCaso());
        assertEquals(entidade.getDataDeAbertura(), dominioMapeado.getDataDeAbertura());
        verify(acaoMapper).paraDominio(acaoEntity);
        verify(parteEnvolvidaMapper).paraDominio(parteEntity);
    }

    @Test
    void deve_mapear_para_dominio_quando_acoes_e_partes_forem_nulos() {
        entidade.setAcoes(null);
        entidade.setPartesEnvolvidas(null);

        Processo dominioMapeado = processoMapper.paraDominio(entidade);

        assertNotNull(dominioMapeado);
        assertEquals(entidade.getId(), dominioMapeado.getId());
        assertEquals(entidade.getStatus(), dominioMapeado.getStatus());
        assertEquals(entidade.getDescricaoDoCaso(), dominioMapeado.getDescricaoDoCaso());
        assertEquals(entidade.getDataDeAbertura(), dominioMapeado.getDataDeAbertura());
    }

    @Test
    void deve_mapear_para_dto() {
        when(acaoMapper.paraDTO(acaoDominio)).thenReturn(acaoDTO);
        when(parteEnvolvidaMapper.paraDTO(parteDominio)).thenReturn(parteDTO);

        ProcessoDTO dtoMapeada = processoMapper.paraDTO(processo);

        assertNotNull(dtoMapeada);
        assertEquals(processo.getId(), dtoMapeada.getId());
        assertEquals(processo.getStatus(), dtoMapeada.getStatusProcesso());
        assertEquals(processo.getDescricaoDoCaso(), dtoMapeada.getDescricaoDoCaso());
        assertEquals(processo.getDataDeAbertura(), dtoMapeada.getDataDeAbertura());
        verify(acaoMapper).paraDTO(acaoDominio);
        verify(parteEnvolvidaMapper).paraDTO(parteDominio);
    }

    @Test
    void deve_mapear_para_dto_quando_acoes_e_partes_forem_nulos() {
        processo.setAcoes(null);
        processo.setPartesEnvolvidas(null);

        ProcessoDTO dtoMapeada = processoMapper.paraDTO(processo);

        assertNotNull(dtoMapeada);
        assertEquals(processo.getId(), dtoMapeada.getId());
        assertEquals(processo.getStatus(), dtoMapeada.getStatusProcesso());
        assertEquals(processo.getDescricaoDoCaso(), dtoMapeada.getDescricaoDoCaso());
        assertEquals(processo.getDataDeAbertura(), dtoMapeada.getDataDeAbertura());
    }


    @Test
    void deve_mapear_para_entidade() {
        when(acaoMapper.paraEntidade(acaoDominio)).thenReturn(acaoEntity);
        when(parteEnvolvidaMapper.paraEntidade(parteDominio)).thenReturn(parteEntity);

        ProcessoEntity entidadeMapeada = processoMapper.paraEntidade(processo);

        assertNotNull(entidadeMapeada);
        assertEquals(processo.getId(), entidadeMapeada.getId());
        assertEquals(processo.getStatus(), entidadeMapeada.getStatus());
        assertEquals(processo.getDescricaoDoCaso(), entidadeMapeada.getDescricaoDoCaso());
        assertEquals(processo.getDataDeAbertura(), entidadeMapeada.getDataDeAbertura());
        verify(acaoMapper).paraEntidade(acaoDominio);
        verify(parteEnvolvidaMapper).paraEntidade(parteDominio);
    }

    @Test
    void deve_mapear_para_entidade_quando_acoes_e_partes_forem_nulos() {
        processo.setAcoes(null);
        processo.setPartesEnvolvidas(null);

        ProcessoEntity entidadeMapeada = processoMapper.paraEntidade(processo);

        assertNotNull(entidadeMapeada);
        assertEquals(processo.getId(), entidadeMapeada.getId());
        assertEquals(processo.getStatus(), entidadeMapeada.getStatus());
        assertEquals(processo.getDescricaoDoCaso(), entidadeMapeada.getDescricaoDoCaso());
        assertEquals(processo.getDataDeAbertura(), entidadeMapeada.getDataDeAbertura());
    }

}
