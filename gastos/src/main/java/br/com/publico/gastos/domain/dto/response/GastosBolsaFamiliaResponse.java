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
public class GastosBolsaFamiliaResponse implements Serializable {

    private String                          valor;
    private String                          quantidadeBeneficiados;
    private MinicipioBolsaFamiliaResponse   municipio;
}
