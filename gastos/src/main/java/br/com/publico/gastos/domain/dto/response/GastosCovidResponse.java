package br.com.publico.gastos.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastosCovidResponse implements Serializable {

    private String programa;
    private String acao;
    private String empenhado;
    private String pago;
    private String liquidado;

}
