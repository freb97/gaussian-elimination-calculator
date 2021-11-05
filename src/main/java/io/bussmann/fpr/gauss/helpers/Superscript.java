package io.bussmann.fpr.gauss.helpers;

/**
 * Superscript.
 *
 * Helper class to generate superscript numbers as strings.
 *
 * @author Frederik Bußmann
 */
public class Superscript {
    /**
     * The generated superscript string.
     */
    private final String superscript;

    /**
     * Class constructor.
     *
     * @param number The number to generate as a superscript string.
     */
    public Superscript(int number) {
        superscript = generateSuperscript(number);
    }

    /**
     * Gets the superscript string.
     *
     * @return The superscript value as a string.
     */
    public String toString() {
        return superscript;
    }

    /**
     * Generates a superscript number string from a given integer.
     *
     * @param number The number to convert.
     *
     * @return The converted number as a string.
     */
    private String generateSuperscript(int number) {
        StringBuilder value = new StringBuilder();
        for (char ch : String.valueOf(number).toCharArray()) {
            value.append((char) ('\u2070' + (ch - '0')));
        }
        return value.toString();
    }
}
