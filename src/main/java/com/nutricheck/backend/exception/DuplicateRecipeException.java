package com.nutricheck.backend.exception;

/**
 * Exception thrown when a duplicate recipe is detected.
 * This exception is used to indicate that an attempt was made to upload
 * a recipe that already exists in the system.
 */
public class DuplicateRecipeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateRecipeException(String message) {
        super(message);
    }
}
