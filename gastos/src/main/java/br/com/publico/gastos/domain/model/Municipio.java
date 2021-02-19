package br.com.publico.gastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Municipio implements Serializable {

    private Long id;
    private String nome;
    Microrregiao microrregiao;
    RegiaoImediata regiaoImediata;

}
