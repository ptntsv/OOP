package org.example;

/**
 * Matcher that based on KMP algorithm.
 */
public class Matcher {

    /**
     * Given substring (or pattern).
     */
    public String pattern;
    /**
     * Length of the result of prefix function.
     */
    private int lastPrefix;
    /**
     * Prefix function values (but only for substring).
     */
    private final int[] prefixes;

    /**
     * Taking a character, calculating value of prefix function and returning either last "chunk"
     * matches the pattern.
     *
     * @param ch Provided character.
     * @return Match the pattern or not.
     */
    public boolean match(char ch) {
        while (lastPrefix > 0 && pattern.charAt(lastPrefix) != ch) {
            lastPrefix = prefixes[lastPrefix - 1];
        }
        if (pattern.charAt(lastPrefix) == ch) {
            lastPrefix++;
        }
        return lastPrefix == pattern.length() - 1;
    }

    /**
     * Resetting matcher context.
     */
    private void reset() {
        this.lastPrefix = 0;
    }

    /**
     * Constructor.
     *
     * @param pattern Pattern (substring).
     */
    public Matcher(String pattern) {
        this.pattern = pattern;
        this.prefixes = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            match(pattern.charAt(i));
            prefixes[i] = this.lastPrefix;
        }
        reset();
    }
}
