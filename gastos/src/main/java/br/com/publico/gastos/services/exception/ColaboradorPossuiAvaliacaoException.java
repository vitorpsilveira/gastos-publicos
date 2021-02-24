package br.com.publico.gastos.services.exception;

import br.com.publico.gastos.services.message.ValidationMessage;

public class ColaboradorPossuiAvaliacaoException extends DomainException {

    public ColaboradorPossuiAvaliacaoException(String nomeColaborador) {
        super(String.format(ValidationMessage.COLABORADOR_POSSUI_AVALIACAO, nomeColaborador));
    }
}
