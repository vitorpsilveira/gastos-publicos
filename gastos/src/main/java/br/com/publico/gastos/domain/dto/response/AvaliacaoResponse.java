package br.com.publico.gastos.domain.dto.response;

import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvaliacaoResponse {

    private Long id;
    private Colaborador colaborador;
    private String tipoAvaliacao;
    private String resultado;
    private String status;
    private String data;
    private BigDecimal nota;
}
