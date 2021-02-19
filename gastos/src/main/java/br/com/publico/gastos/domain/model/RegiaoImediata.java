package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegiaoImediata implements Serializable {

    private Long id;
    private String nome;
    RegiaoIntermediaria regiaoIntermediaria;
}
