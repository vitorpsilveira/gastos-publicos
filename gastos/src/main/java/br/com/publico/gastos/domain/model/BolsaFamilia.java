package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BolsaFamilia implements Serializable {
    String                  id;
    String                  dataReferencia;
    MunicipioBolsaFamilia   municipio;
    public String                 valor;
    Tipo                    tipo;
    public String quantidadeBeneficiados;

}

