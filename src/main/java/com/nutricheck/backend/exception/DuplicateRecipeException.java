package com.nutricheck.backend.exception;

public class DuplicateRecipeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateRecipeException(String message) {
        super(message);
    }
}
