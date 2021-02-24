package br.com.publico.gastos.services.exception;

import br.com.publico.gastos.services.message.ValidationMessage;

public class ColaboradorPossuiAvaliacaoException extends DomainException {

    public ColaboradorPossuiAvaliacaoException(String message) {
        super(message);
    }

    public ColaboradorPossuiAvaliacaoException(Long colaboradorId) {
        super(String.format(ValidationMessage.COLABORADOR_POSSUI_AVALIACAO, colaboradorId));
    }
}
