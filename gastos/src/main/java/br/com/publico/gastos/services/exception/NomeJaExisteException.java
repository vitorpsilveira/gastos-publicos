package br.com.publico.gastos.services.exception;

import br.com.publico.gastos.services.message.ValidationMessage;

public class NomeJaExisteException extends DomainException {

    public NomeJaExisteException(String nome) {
        super(String.format(ValidationMessage.O_NOME_JA_EXISTE, nome));
    }
}
