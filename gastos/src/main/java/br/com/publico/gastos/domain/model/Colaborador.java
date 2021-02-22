package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador extends EntityBase {

    private String nome;

    @Column(length = 3)
    private String sigla;

    @OneToMany(mappedBy = "colaborador")
    private List<Avaliacao> avaliacoes;

}
