package io.github.go.planeja.infra.handlers;

import io.github.go.planeja.common.exceptions.RegistroNaoEncontradoException;
import io.github.go.planeja.common.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity <?> handleValidationException(ValidationException ex) {
        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of("timestamp", LocalDateTime.now(),
                "status", status.value(),
                "message", ex.getMessage(),
                "camposInvalidos", ex.getCamposInvalidos()
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var camposInvalidos = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of(
                        "campo", fieldError.getField(),
                        "mensagem", fieldError.getDefaultMessage()
                ))
                .toList();

        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of("timestamp", LocalDateTime.now(),
                "status", status.value(),
                "camposInvalidos", camposInvalidos
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity <?> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND,
                        "erro", ex.getMessage(),
                        "message", ex.getMessage()
                ));
    }
}
