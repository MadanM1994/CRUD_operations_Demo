package com.madan.crud_operations_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "You client side request is incorrect. Please correct it and retry......";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        String error = "Something went wrong. Please contact support.";
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException() {
        String error = "The Employee record associated with the ID is not found Please enter valid employee ID";
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeWithMatchNotFoundException.class)
    public ResponseEntity<Object> EmployeeWithMatchNotFoundException() {
        String error = " The Employee record associated with the given first name is not found Please enter valid employee Name";
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
