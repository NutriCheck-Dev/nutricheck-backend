package com.nutricheck.backend.exception;

public class RecipeNotFoundException extends Exception {
    private static final long serialVersionUID = 2L;

    public RecipeNotFoundException(String message) {
        super(message);
    }

}
