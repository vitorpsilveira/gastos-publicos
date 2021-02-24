package br.com.publico.gastos.services.exception;

public class EntidadeNaoEncontradaException extends DomainException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(String colaboradorNaoEncontrado, Long colaboradorId) {
        super(String.format(colaboradorNaoEncontrado, colaboradorId));
    }
}
