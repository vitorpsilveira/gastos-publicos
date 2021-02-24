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

    @NotNull
    private Short resultado;

    @NotNull
    private Short status;

    @NotNull
    private LocalDate data;

    @NotNull
    private BigDecimal nota;
}
