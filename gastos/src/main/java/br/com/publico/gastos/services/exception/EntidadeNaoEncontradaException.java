package br.com.publico.gastos.services.exception;

public class EntidadeNaoEncontradaException extends DomainException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(String mensagem, Long entidadeId) {
        super(String.format(mensagem, entidadeId));
    }
}
