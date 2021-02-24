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

    public static Status fromString(final Short id) {
        switch (id) {
            case 1:
                return NOVO;
            case 2:
                return AGENDADO;
            case 3:
                return FORMALIZAR;
            case 4:
                return EM_APROVACAO;
            case 5:
                return FINALIZADO;
            case 6:
                return DESLIGADO;
            default:
                return null;
        }
    }
}