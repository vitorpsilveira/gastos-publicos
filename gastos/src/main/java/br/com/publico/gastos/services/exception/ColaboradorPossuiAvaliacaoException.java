package br.com.publico.gastos.services.exception;

import br.com.publico.gastos.services.message.ValidationMessage;

public class ColaboradorPossuiAvaliacaoException extends DomainException {

    public ColaboradorPossuiAvaliacaoException(String nomeColaborador) {
        super(String.format(ValidationMessage.COLABORADOR_POSSUI_AVALIACAO, nomeColaborador));
    }

    public ColaboradorPossuiAvaliacaoException(int mes) {
        super(String.format(ValidationMessage.COLABORADOR_POSSUI_AVALIACAO_NO_MES, mes));
    }

    public ColaboradorPossuiAvaliacaoException(int linha, String nomeColaborador, int mes) {
        super(String.format(ValidationMessage.COLABORADOR_POSSUI_AVALIACAO_EXCEL, linha, nomeColaborador, mes));
    }
}
