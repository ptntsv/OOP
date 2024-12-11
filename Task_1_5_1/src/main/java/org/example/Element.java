package org.example;

/**
 * Base Markdown element.
 */
public abstract class Element {

    /**
     * Default indentation width.
     */
    public int indentationWidth = 4;

    /**
     * Indentation level.
     */
    public int indentationLvl = 0;

    /**
     * Indents element as indentation level * indentation width.
     *
     * @param str String to indent.
     * @return Indented string.
     */
    public String indent(String str) {
        return " ".repeat(indentationLvl * indentationWidth) + str;
    }
}
