package io.bussmann.fpr.gauss.helpers;

/**
 * Subscript.
 *
 * Helper class to generate subscript numbers as strings.
 *
 * @author Frederik Bußmann
 */
public class Subscript {
    /**
     * The generated subscript string.
     */
    private final String subscript;

    /**
     * Class constructor.
     *
     * @param number The number to generate as a subscript string.
     */
    public Subscript(int number) {
        subscript = generateSubscript(number);
    }

    /**
     * Gets the subscript string.
     *
     * @return The subscript value as a string.
     */
    public String toString() {
        return subscript;
    }

    /**
     * Generates a subscript number string from a given integer.
     *
     * @param number The number to convert.
     *
     * @return The converted number as a string.
     */
    private String generateSubscript(int number) {
        StringBuilder value = new StringBuilder();
        for (char ch : String.valueOf(number).toCharArray()) {
            value.append((char) ('\u2080' + (ch - '0')));
        }
        return value.toString();
    }
}
