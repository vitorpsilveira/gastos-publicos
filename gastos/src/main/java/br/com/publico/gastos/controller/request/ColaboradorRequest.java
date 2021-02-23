package br.com.publico.gastos.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColaboradorRequest {

    @NotBlank(message = "O nome é obritatório")
    private String nome;

    @NotBlank(message = "A sigla é obritatória")
    private String sigla;
}
