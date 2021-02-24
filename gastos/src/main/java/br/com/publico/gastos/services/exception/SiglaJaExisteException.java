package br.com.publico.gastos.services.exception;

import br.com.publico.gastos.services.message.ValidationMessage;

public class SiglaJaExisteException extends DomainException {

    public SiglaJaExisteException(String sigla) {
        super(String.format(ValidationMessage.A_SIGLA_JA_EXISTE, sigla));
    }
}
