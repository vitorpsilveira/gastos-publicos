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
public class ConsultaMunicipioResponse implements Serializable {

    private Long id;
    private String nome;
    private String uf;

}
