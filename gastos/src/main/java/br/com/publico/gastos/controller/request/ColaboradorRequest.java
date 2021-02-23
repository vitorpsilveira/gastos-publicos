package br.com.publico.gastos.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColaboradorRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A sigla é obrigatória")
    private String sigla;

    private Integer teste;
}
