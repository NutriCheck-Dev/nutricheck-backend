package com.nutricheck.backend.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataSize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;


/**
 * Global exception handler for this Nutricheck backend.
 * This class handles various exceptions that may occur during the execution of the application
 * and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final DataSize maxFileSize;


    public GlobalExceptionHandler(@Value("${spring.servlet.multipart.max-file-size}") DataSize maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    /**
     * Handles MethodArgumentNotValidException, which occurs when DTO validation fails.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
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

    /**
     * Handles IOException, whiccan occur when handling the image file of a meal.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    /**
     * Handles a MaxUploadSizeExceededException, which occurs when the uploaded file exceeds the maximum allowed size
     * in the application.properties.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.PAYLOAD_TOO_LARGE,
                "The uploaded file exceeds the maximum allowed size of " + maxFileSize.toString() + ".");
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
    }
}
