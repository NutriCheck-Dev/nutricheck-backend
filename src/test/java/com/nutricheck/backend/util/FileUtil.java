package com.nutricheck.backend.util;


import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for file operations.
 */
public final class FileUtil {
    private FileUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Reads the content of a file as a string.
     *
     * @param filePath the path to the file
     * @return the content of the file as a string
     */
    public static String readFileAsString(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        try (InputStream is = resource.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
