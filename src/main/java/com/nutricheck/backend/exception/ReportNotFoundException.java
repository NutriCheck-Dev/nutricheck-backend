package com.nutricheck.backend.exception;

public class ReportNotFoundException extends Exception {
    private static final long serialVersionUID = 3L;

    public ReportNotFoundException(String message) {
        super(message);
    }
}
