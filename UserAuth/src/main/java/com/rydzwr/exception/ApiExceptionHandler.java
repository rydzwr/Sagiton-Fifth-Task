package com.rydzwr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity<Object> handleException(InvalidCredentialsException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, notFound);
    }

    @ExceptionHandler(value = {UnauthorizedCallException.class})
    public ResponseEntity<Object> handleException(UnauthorizedCallException e) {
        HttpStatus forbidden = HttpStatus.FORBIDDEN;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                forbidden,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, forbidden);
    }
}
