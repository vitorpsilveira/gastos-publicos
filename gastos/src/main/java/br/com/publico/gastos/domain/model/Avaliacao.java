package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "avaliacao")
public class Avaliacao extends EntityBase {

    @ManyToOne(optional = false)
    @JoinColumn(name = "colaborador_id", nullable = false)
    @NotNull(message = "O colaborador é obrigatório")
    private Colaborador colaborador;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo da avalição é obrigatório")
    private TipoAvaliacao tipoAvaliacao;

    @Enumerated(EnumType.STRING)
    private TipoResultado resultado;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate data;

    private BigDecimal nota;
}
