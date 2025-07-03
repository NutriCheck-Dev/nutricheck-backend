package com.nutricheck.backend.exception;

public class ReportNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public ReportNotFoundException(String message) {
        super(message);
    }
}
