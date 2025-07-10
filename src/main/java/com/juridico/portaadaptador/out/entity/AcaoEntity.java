package com.juridico.portaadaptador.out.entity;

import com.juridico.dominio.model.enums.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACAO", schema = "JURIDICO")
public class AcaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TIPO")
    private TipoAcao tipo;
    @Column(name = "DATA_REGISTRO")
    private LocalDateTime dataRegistro;
    @Column(name = "DESCRICAO")
    private String descricao;
}
