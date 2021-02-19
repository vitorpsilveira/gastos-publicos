package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BolsaFamilia implements Serializable {
    private String                  id;
    private String                  dataReferencia;
    private MunicipioBolsaFamilia   municipio;
    private Integer                 valor;
    private Tipo                    tipo;
    private Integer                 quantidadeBeneficiados;

}

