package com.madan.crud_operations_demo.exception;

public class EmployeeWithMatchNotFoundException extends RuntimeException {
    public EmployeeWithMatchNotFoundException(String message) {
        super(message);
    }
}
