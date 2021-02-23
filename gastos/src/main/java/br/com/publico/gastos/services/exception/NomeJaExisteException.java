package br.com.publico.gastos.services.exception;

public class NomeJaExisteException extends DomainException {

    public NomeJaExisteException(String message) {
        super(message);
    }
}
