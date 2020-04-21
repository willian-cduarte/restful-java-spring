package com.will.udemy.microservices.infrastructure.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.udemy.microservices.infrastructure.formatter.DatePatternFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

    @Autowired
    DatePatternFormatting format;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        Map<String, Object> params = new HashMap<>();
        params.put("path", request.getDescription(false));

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                format.toStringTimestamp(new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(), params);

        logger.error(request.getContextPath() , ex);
        logger.error(request.getContextPath() , exceptionResponse);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleViolationExceptions(ConstraintViolationException ex, WebRequest request) {
        Map<String, Object> details, fields;
        fields = new HashMap<>();

        ex.getConstraintViolations().forEach(constraintViolation -> {
            fields.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        });

        details = new HashMap<>();

        details.put("fields", fields);
        //details.put("path", request.getDescription(false));

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                format.toStringTimestamp(new Date()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(), details);

        logger.error(request.getContextPath() , ex);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RswNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(
            RswNotFoundException ex, WebRequest request
    ) throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();
        params.put("path", request.getDescription(false));
        String details = new ObjectMapper().writeValueAsString(params);

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                format.toStringTimestamp(new Date()),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(), params
        );

        logger.error(request.getContextPath() , ex);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("path", request.getDescription(false));
        params.put("message", ex.getMessage());
        params.put("localizedMessage", ex.getLocalizedMessage());
        params.put("cause", ex.getCause());
        params.put("errors", ex.getBindingResult().getAllErrors());
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                format.toStringTimestamp(new Date()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getBindingResult().getNestedPath(),
                params
        );
        logger.error(request.getContextPath() , ex);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



    /*@SneakyThrows
    @Override
    protected ResponseEntity handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }
        Map<String, Object> params = new HashMap<>();

        ExceptionResponse er;
        *//*if (body == null) {

            params.put("message", ex.getMessage());
            params.put("localizedMessage", ex.getLocalizedMessage());
            params.put("cause", ex.getCause());

            er = new ExceptionResponse(
                    new Date(),
                    status.value(),
                    status.getReasonPhrase(),
                    ex.getMessage(),
                    params
            );
        } else {*//*
            params.put("cause", body);

            er = new ExceptionResponse(
                    new Date(),
                    status.value(),
                    status.getReasonPhrase(),
                    ex.getMessage(),
                    params

            );
       // }
        return new ResponseEntity<>(er, status);
    }*/

}
