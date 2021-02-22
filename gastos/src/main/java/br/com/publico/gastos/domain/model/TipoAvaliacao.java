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
}