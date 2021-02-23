package br.com.publico.gastos.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvaliacaoRequest {

    private Long colaborador;
    private Short tipoAvaliacao;
    private Short resultado;
    private Short status;
    private String data;
    private BigDecimal nota;
}
