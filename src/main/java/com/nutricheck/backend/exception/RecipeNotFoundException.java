package com.nutricheck.backend.exception;

/**
 * Exception thrown when a recipe is not found.
 * This exception is used to indicate that a requested recipe does not exist in the system.
 */
public class RecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
