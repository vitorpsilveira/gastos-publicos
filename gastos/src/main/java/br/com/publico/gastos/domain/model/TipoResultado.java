package br.com.publico.gastos.domain.model;

import lombok.Getter;

@Getter
public enum TipoResultado {
    MERITO("Mérito"),
    PROMOCAO("Promoção"),
    ADEQUACAO("Adequação"),
    NA("N/A");

    private String descricao;

    TipoResultado(String descricao) {
        this.descricao = descricao;
    }
}