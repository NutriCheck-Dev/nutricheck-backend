package com.nutricheck.backend;

/**
 * Utility class containing static helper methods and constants.
 * This class is not meant to be instantiated.
 */
public final class Utils {
    private static final double EPSILON = 1E-6;
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Compares two double values for equality within a small tolerance (epsilon).
     * This method helps to avoid issues with floating-point precision.
     *
     * @param a the first double value
     * @param b the second double value
     * @return 0 if the values are considered equal, -1 if a < b, and 1 if a > b
     */
    public static int compareDouble(double a, double b) {
        if(Math.abs(a - b) < EPSILON) {
            return 0;
        } else {
            return a < b ? -1 : 1;
        }
    }
}
