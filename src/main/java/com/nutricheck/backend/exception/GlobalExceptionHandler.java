package com.nutricheck.backend.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Global exception handler for this Nutricheck backend.
 * This class handles various exceptions that may occur during the execution of the application
 * and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException, which occurs when DTO validation fails.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(ex, ex.getBody()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
    /**
     * Handles ConstraintViolationException, which occurs when a constraint
     * on the path variables is violated.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    /**
     * Handles DuplicateRecipeException, which occurs when a duplicate recipe is detected.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = DuplicateRecipeException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRecipeException(DuplicateRecipeException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
/**
     * Handles RecipeNotFoundException, which occurs when a recipe is not found.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecipeNotFoundException(RecipeNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    /**
     * Handles ReportNotFoundException, which occurs when a report is not found.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = ReportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReportNotFoundException(ReportNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
