package com.juridico.dominio.model;

import com.juridico.dominio.model.enums.TipoParteEnvolvida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParteEnvolvida {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;
    private TipoParteEnvolvida tipoParteEnvolvida;

    public ParteEnvolvida(String nome, String cpfCnpj, String telefone, String email, TipoParteEnvolvida tipoParteEnvolvida) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (cpfCnpj == null || cpfCnpj.isBlank()) {
            throw new IllegalArgumentException("CPF/CNPJ não pode ser nulo ou vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }
        if (tipoParteEnvolvida == null) {
            throw new IllegalArgumentException("Tipo de parte envolvida não pode ser nulo.");
        }
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.tipoParteEnvolvida = tipoParteEnvolvida;
    }
}
