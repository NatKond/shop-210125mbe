package de.telran.shop210125mbe.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception) {
        return "Controller(Exception): " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception) {
        return "Sorry, an error has occurred : " + exception.getMessage() + " Please try again later.";
    }
}
