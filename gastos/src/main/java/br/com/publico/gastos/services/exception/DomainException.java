package br.com.publico.gastos.services.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
