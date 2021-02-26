package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Colaborador extends EntityBase {

    @NotBlank(message = "O nome é obrigatório")
    @Length(max = 100, message = "O nome deve possuir no máximo 100 caracterres")
    @Column(length = 100)
    private String nome;

    @NotBlank(message = "A sigla é obrigatório")
    @Length(max = 3, message = "A sigla deve possuir, no máximo 3 caracteres")
    @Column(length = 3)
    private String sigla;

    @OneToMany(mappedBy = "colaborador")
    @JsonIgnore
    private List<Avaliacao> avaliacoes;

}