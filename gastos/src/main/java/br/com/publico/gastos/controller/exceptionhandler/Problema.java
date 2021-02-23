package br.com.publico.gastos.controller.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problema {

    private String mensagem;
    private Integer codigoErro;

}
