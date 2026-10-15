package org.example.oficinaapi.config.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
public class ErrorEntity {

    private String mensagem;

    private HttpStatus status;

    private LocalDate data;
}
