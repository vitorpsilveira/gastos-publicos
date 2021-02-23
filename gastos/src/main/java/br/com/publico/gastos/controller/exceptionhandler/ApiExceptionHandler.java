package br.com.publico.gastos.controller.exceptionhandler;

import br.com.publico.gastos.controller.exceptionhandler.message.ExceptionHandlerMessage;
import br.com.publico.gastos.services.exception.DomainException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        String mensagem = "";
        List<String> mensagens = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()))
                .collect(Collectors.toList());
        mensagem = mensagens.stream().reduce((acc, msg) -> acc + "; " + msg).get();
        Problema problema = criarProblema(status, mensagem);
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problema problema = Problema.builder().
                codigoErro(status.value())
                .mensagem(String.format(ExceptionHandlerMessage.RECURSO_NAO_ENCONTRADO, ex.getRequestURL()))
                .build();
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .mensagem(ExceptionHandlerMessage.OCORREU_UM_ERRO_INESPERADO)
                    .codigoErro(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable causaRaiz = ExceptionUtils.getRootCause(ex.getRootCause());
        if (causaRaiz instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) causaRaiz, headers, status, request);
        } else if (causaRaiz instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) causaRaiz, headers, status, request);
        }
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationExcetion(ConstraintViolationException ex, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String mensagem = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        Problema problema = Problema.builder()
                    .codigoErro(badRequest.value())
                    .mensagem(mensagem)
                    .build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), badRequest, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String mensagem = ex.getMessage();
        Problema problema = criarProblema(badRequest, mensagem);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), badRequest, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException causaRaiz, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String pathObject = getPathObject(causaRaiz.getPath());
        String mensagem = String.format(ExceptionHandlerMessage.FORMATO_INVALIDO, pathObject, causaRaiz.getTargetType().getSimpleName());
        Problema problema = Problema.builder()
                .codigoErro(status.value())
                .mensagem(mensagem)
                .build();
        return handleExceptionInternal(causaRaiz, problema, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException causaRaiz, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String pathObject = getPathObject(causaRaiz.getPath());
        String mensagem = String.format(ExceptionHandlerMessage.PROPRIEDADE_INEXISTENTE, pathObject);
        Problema problema = Problema.builder()
                .codigoErro(status.value())
                .mensagem(mensagem)
                .build();
        return handleExceptionInternal(causaRaiz, problema, headers, status, request);
    }

    private String getPathObject(List<JsonMappingException.Reference> path) {
        return path.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private Problema criarProblema(HttpStatus httpStatus, String mensagem) {
        return Problema.builder()
                .codigoErro(httpStatus.value())
                .mensagem(mensagem)
                .build();
    }
}
