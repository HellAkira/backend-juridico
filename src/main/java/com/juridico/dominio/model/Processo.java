package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.StatusProcesso;
import com.juridico.dominio.model.enums.TipoAcao;
import com.juridico.dominio.model.enums.TipoParteEnvolvida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Processo {
    private Long id;
    private LocalDate dataDeAbertura;
    private String descricaoDoCaso;
    private StatusProcesso status;
    private List<Acao> acoes;
    private List<ParteEnvolvida> partesEnvolvidas;

    public Processo(LocalDate dataDeAbertura, String descricaoDoCaso, StatusProcesso status) {
        validarParametrosObrigatorios(dataDeAbertura, descricaoDoCaso, status);
        this.dataDeAbertura = dataDeAbertura;
        this.descricaoDoCaso = descricaoDoCaso;
        this.status = status;
    }

    private static void validarParametrosObrigatorios(LocalDate dataDeAbertura,
                                                      String descricaoDoCaso,
                                                      StatusProcesso status) {
        if (dataDeAbertura == null) {
            throw new IllegalArgumentException("Data de abertura não pode ser nula.");
        }
        if (descricaoDoCaso == null || descricaoDoCaso.isBlank()) {
            throw new IllegalArgumentException("Descrição do caso não pode ser nula ou vazia.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status do processo não pode ser nula ou vazia.");
        }
    }

    public void arquivar() {
        if (status == StatusProcesso.ARQUIVADO) {
            throw new IllegalStateException("Processo já está arquivado.");
        }
        status = StatusProcesso.ARQUIVADO;
    }

    public void adicionarAcao(TipoAcao tipo, String descricao) {
        if (acoes == null) {
            acoes = new ArrayList<>();
        }
        acoes.add(new Acao(tipo, descricao));
    }

    public void adicionarParteEnvolvida(String nome,
                                        String cpfCnpj,
                                        String telefone,
                                        String email,
                                        TipoParteEnvolvida tipoParteEnvolvida) {
        if (partesEnvolvidas == null) {
            partesEnvolvidas = new ArrayList<>();
        }
        partesEnvolvidas.add(new ParteEnvolvida(nome, cpfCnpj, telefone, email, tipoParteEnvolvida));
    }
}
