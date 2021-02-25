package br.com.publico.gastos.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AvaliacaoRequest {

    @NotNull
    private Long colaborador;

    @NotNull
    private Short tipoAvaliacao;

    private Short resultado;
    private Short status;
    private LocalDate data;
    private BigDecimal nota;
}
