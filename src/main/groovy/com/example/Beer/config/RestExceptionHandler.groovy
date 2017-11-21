package com.example.Beer.config

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler([BeerNotFoundException.class])
    protected ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
        handleExceptionInternal(e, "Beer Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler([DataIntegrityViolationException.class])
    protected ResponseEntity<Object> handleBadRequest(Exception e, WebRequest request) {
        handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

}
