package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao extends EntityBase {

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
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
