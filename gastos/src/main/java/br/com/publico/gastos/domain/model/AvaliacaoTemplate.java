package br.com.publico.gastos.domain.model;

import br.com.publico.gastos.domain.model.base.EntityBase;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "avaliacao")
@Data
public class AvaliacaoTemplate extends EntityBase {

    private String nome;
    private String tipoAvaliacao;
    private Date data;
    private String status;
    private Double nota;
    private String resultado;

}
