package org.example;

import java.util.Arrays;

/**
 * Base Markdown element.
 */
public abstract class Element {

    /**
     * Default indentation width.
     */
    private int indentationWidth = 4;

    /**
     * Indentation width getter.
     *
     * @return Indentation width.
     */
    protected int getIndentationWidth() {
        return indentationWidth;
    }

    /**
     * Indentation width setter.
     */
    protected void setIndentationWidth(int width) {
        indentationWidth = width;
    }

    /**
     * Indentation level.
     */
    private int indentationLvl = 0;

    /**
     * Indentation level getter.
     *
     * @return Indentation level.
     */
    protected int getIndentationLvl() {
        return indentationLvl;
    }

    /**
     * Indentation level setter.
     */
    protected void setIndentationLvl(int lvl) {
        indentationLvl = lvl;
    }

    /**
     * Indents element as indentation level * indentation width.
     *
     * @param str String to indent.
     * @return Indented string.
     */
    public String indent(String str) {
        return Arrays.stream(str.split("\n")).reduce("",
            (String prev, String line) -> prev + " ".repeat(indentationLvl * indentationWidth)
                + line + '\n').stripTrailing();
    }

    /**
     * toString method.
     *
     * @return String representation.
     */
    public abstract String toString();
}
