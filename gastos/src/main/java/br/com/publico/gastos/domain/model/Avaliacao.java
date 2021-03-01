package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import br.com.publico.gastos.domain.model.converter.StatusConverter;
import br.com.publico.gastos.domain.model.converter.TipoAvaliacaoConverter;
import br.com.publico.gastos.domain.model.converter.TipoResultadoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "colaborador_id", nullable = false)
    @NotNull(message = "O colaborador é obrigatório")
    private Colaborador colaborador;


    @Convert(converter = TipoAvaliacaoConverter.class)
    @NotNull(message = "O tipo da avalição é obrigatório")
    private TipoAvaliacao tipoAvaliacao;

    @Convert(converter = TipoResultadoConverter.class)
    private TipoResultado resultado;

    @Convert(converter = StatusConverter.class)
    private Status status;

    private LocalDate data;

    private BigDecimal nota;
}
