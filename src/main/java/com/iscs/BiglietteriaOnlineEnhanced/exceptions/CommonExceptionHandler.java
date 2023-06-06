package com.iscs.BiglietteriaOnlineEnhanced.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ClienteNotFoundException.class, VoloNotFoundException.class, AeroportoNotFoundException.class,
            BigliettoNotFoundExeption.class, TooManyBigliettiException.class})
    public final ResponseEntity<Object> handleNotFoundExceptions(Exception exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return handleExceptionInternal(exception, exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}

