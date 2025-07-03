package com.nutricheck.backend.exception;

public class RecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public RecipeNotFoundException(String message) {
        super(message);
    }

}
