package com.ecommerce.project.exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return errors;
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatusException(ResponseStatusException ex) {

        return new ErrorResponse(
                ex.getStatusCode().value(),
                ex.getReason(),
                LocalDateTime.now()
        );
    }
}
