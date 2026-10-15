package org.example.oficinaapi.config.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ErrorEntity> EntityNotFound(EntityNotFoundException exception) {
        ErrorEntity erro = new ErrorEntity("Entidade não encontrada!", HttpStatus.NOT_FOUND, LocalDate.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
