package com.nutricheck.backend.exception;

/**
 * Exception thrown when a recipe is not found.
 * This exception is used to indicate that a requested recipe does not exist in the system.
 */
public class ReportNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public ReportNotFoundException(String message) {
        super(message);
    }
}
