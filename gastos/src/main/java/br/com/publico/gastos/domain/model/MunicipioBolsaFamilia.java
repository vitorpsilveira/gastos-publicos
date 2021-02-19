package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MunicipioBolsaFamilia implements Serializable {
        private String             codigoIBGE;
        private String             nomeIBGE;
        private String             nomeIBGEsemAcento;
        private String             pais;
        private String             nomeRegiao;
        private String             codigoRegiao;
        private UFBolsaFamilia     uf;
}
