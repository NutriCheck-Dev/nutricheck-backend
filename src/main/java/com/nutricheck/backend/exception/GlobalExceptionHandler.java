package com.nutricheck.backend.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return null; // get problem detail from exception and return it
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException ex) {
        return null; // construct problem detail
    }
    @ExceptionHandler(value = DuplicateRecipeException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateRecipeException(DuplicateRecipeException ex) {
        return null; // construct problem detail
    }
    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleRecipeNotFoundException(RecipeNotFoundException ex) {
        return null; // construct problem detail
    }
    @ExceptionHandler(value = ReportNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleReportNotFoundException(ReportNotFoundException ex) {
        return null; // construct problem detail
    }
}
