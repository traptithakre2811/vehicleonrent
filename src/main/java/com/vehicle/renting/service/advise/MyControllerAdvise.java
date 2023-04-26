package com.vehicle.renting.service.advise;

import com.vehicle.renting.service.exception.EmptyInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvise  {
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handlerEmptyInput(EmptyInputException emptyInputException)
    {
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlerNoSuchElementException(NoSuchElementException noSuchElementException)
    {
        return new ResponseEntity<String>("No value is present in db, please fill valid id",HttpStatus.NOT_FOUND);
    }
}
