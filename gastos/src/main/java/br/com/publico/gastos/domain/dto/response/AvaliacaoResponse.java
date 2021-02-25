package br.com.publico.gastos.domain.dto.response;

import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvaliacaoResponse {

    private Long idColaborador;
    private TipoAvaliacao tipoAvaliacao;
    private TipoResultado resultado;
    private Status status;
    private String data;
    private BigDecimal nota;
}
