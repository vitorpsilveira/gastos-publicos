package br.com.publico.gastos.domain.model;

import lombok.Getter;

@Getter
public enum TipoAvaliacao {

    ONE_TO_ONE("1:1"),
    INFORMAL("Informal"),
    EXPERIENCIA45D("Experiência de 45 dias"),
    EXPERIENCIA90D("Experiência de 90 dias"),
    RECONHECIMENTO("Reconhecimento"),
    FORMACAO("Formação");

    private String descricao;

    TipoAvaliacao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoAvaliacao fromShort(final Short id) {
        switch (id) {
            case 1:
                return ONE_TO_ONE;
            case 2:
                return INFORMAL;
            case 3:
                return EXPERIENCIA45D;
            case 4:
                return EXPERIENCIA90D;
            case 5:
                return RECONHECIMENTO;
            case 6:
                return FORMACAO;
            default:
                return null;
        }
    }
}