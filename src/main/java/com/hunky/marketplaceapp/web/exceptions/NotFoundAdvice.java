package com.hunky.marketplaceapp.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(NotFoundException nfe) {
        return nfe.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notEnoughMoneyHandler(NotEnoughMoneyException neme) {
        return neme.getMessage();
    }

}