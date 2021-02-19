package br.com.publico.gastos.domain.dto.response;

import br.com.publico.gastos.domain.model.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinicipioBolsaFamiliaResponse implements Serializable {

    private String nomeIBGE;
    private String nomeRegiao;
    private String uf;
}


