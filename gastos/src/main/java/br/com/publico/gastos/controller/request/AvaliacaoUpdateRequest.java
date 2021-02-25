package br.com.publico.gastos.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AvaliacaoUpdateRequest {

    private Short tipoAvaliacao;
    private Short resultado;
    private Short status;
    private LocalDate data;
    private BigDecimal nota;
}
