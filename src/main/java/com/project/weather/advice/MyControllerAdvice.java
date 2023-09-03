package com.project.weather.advice;

import com.project.weather.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handelInvalidInput(InvalidInputException invalidInputException) {
        return new ResponseEntity<String>("Please enter a valid city name", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handelInvalidInput(HttpClientErrorException invalidDataException) {
        return new ResponseEntity<String>("Please enter a valid city name", HttpStatus.NOT_FOUND);
    }


}
