package com.juridico.portaadaptador.out.entity;

import com.juridico.dominio.model.enums.StatusProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROCESSO", schema = "JURIDICO")
public class ProcessoEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_DE_ABERTURA")
    private LocalDate dataDeAbertura;

    @Column(name = "DESCRICAO_DO_CASO")
    private String descricaoDoCaso;

    @Column(name = "STATUS")
    private StatusProcesso status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PROCESSO_ID")
    private List<ParteEnvolvidaEntity> partesEnvolvidas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PROCESSO_ID")
    private List<AcaoEntity> acoes = new ArrayList<>();
}
