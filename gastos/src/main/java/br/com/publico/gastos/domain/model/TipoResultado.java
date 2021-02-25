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

    public static TipoResultado fromShort(final Short id) {
        switch (id) {
            case 1:
                return MERITO;
            case 2:
                return PROMOCAO;
            case 3:
                return ADEQUACAO;
            case 4:
                return NA;
            default:
                return null;
        }
    }
}