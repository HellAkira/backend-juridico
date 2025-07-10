package com.juridico.portaadaptador.entity;

import com.juridico.comum.enums.TipoParteEnvolvida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARTE_ENVOLVIDA", schema = "JURIDICO")
public class ParteEnvolvidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "TIPO_PARTE_ENVOLVIDA")
    private TipoParteEnvolvida tipoParteEnvolvida;
}
