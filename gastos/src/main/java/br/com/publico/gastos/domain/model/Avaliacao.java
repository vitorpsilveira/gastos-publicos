package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao extends EntityBase {

    @ManyToOne(optional = false)
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @Enumerated(EnumType.STRING)
    private TipoAvaliacao avaliacao;

    @Enumerated(EnumType.STRING)
    private TipoResultado resultado;
}
