package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tipo implements Serializable {
    private Integer id;
    private String  descricao;
    private String  descricaoDetalhada;
}
