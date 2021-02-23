package br.com.publico.gastos.domain.model;

import lombok.Getter;

@Getter
public enum Status {
    NOVO("Novo"),
    AGENDADO("Agendado"),
    FORMALIZAR("Formalizar"),
    EM_APROVACAO("Em aprovação"),
    FINALIZADO("Finalizado"),
    DESLIGADO("Desligado");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
