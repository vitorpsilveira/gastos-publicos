package br.com.publico.gastos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvaliacaoDTO {

    private Long id;
    private String mesAno;
    private BigDecimal nota;
    private String resultado;
}
