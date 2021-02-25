package br.com.publico.gastos.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorResponse {

    private Long id;
    private String nome;
    private String sigla;
    private Boolean temAvaliacao;
}
