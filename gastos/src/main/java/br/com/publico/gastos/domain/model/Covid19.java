package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Covid19 implements Serializable {

    private String mesAno;
    private String codigoFuncao;
    private String funcao;
    private String codigoSubfuncao;
    private String subfuncao;
    private String codigoPrograma;
    private String programa;
    private String codigoAcao;
    private String acao;
    private String idPlanoOrcamentario;
    private String codigoPlanoOrcamentario;
    private String planoOrcamentario;
    private String codigoGrupoDespesa;
    private String grupoDespesa;
    private String codigoElementoDespesa;
    private String elementoDespesa;
    private String codigoModalidadeDespesa;
    private String modalidadeDespesa;
    private String empenhado;
    private String pago;
    private String liquidado;

}
