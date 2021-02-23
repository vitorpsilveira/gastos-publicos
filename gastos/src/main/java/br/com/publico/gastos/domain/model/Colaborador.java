package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "colaborador", uniqueConstraints = { @UniqueConstraint(name = "unq_sigla", columnNames = "sigla") })
public class Colaborador extends EntityBase {

    @NotBlank(message = "O nome é obrigatório")
    @Column(length = 100)
    private String nome;

    @NotBlank(message = "A sigla é obrigatório")
    @Length(max = 3, message = "A sigla deve possuir, no máximo 3 caracteres")
    @Column(length = 3)
    private String sigla;

    @OneToMany(mappedBy = "colaborador")
    private List<Avaliacao> avaliacoes;

}