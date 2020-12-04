package com.bosch.employeeman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bosch.employeeman.exception.EmployeeNotfoundException;

@ControllerAdvice
public class EmployeeExceptionController
{
    @ExceptionHandler(value = EmployeeNotfoundException.class)
    public ResponseEntity<Object> exception(EmployeeNotfoundException exception)
    {
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
