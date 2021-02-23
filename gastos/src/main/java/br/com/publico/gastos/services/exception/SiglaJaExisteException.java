package br.com.publico.gastos.services.exception;

public class SiglaJaExisteException extends DomainException {

    public SiglaJaExisteException(String message) {
        super(message);
    }
}
