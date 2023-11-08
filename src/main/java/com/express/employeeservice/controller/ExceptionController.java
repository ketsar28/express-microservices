package com.express.employeeservice.controller;

import com.express.employeeservice.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException(ResponseStatusException exception) {
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .errors(exception.getReason())
                .statusCode(exception.getStatus().value())
                .build();

        return ResponseEntity
                .status(exception.getStatus())
                .body(commonResponse);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception) {
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .errors(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(commonResponse);
    }
}
