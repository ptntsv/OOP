package org.example;

public abstract class Element {

    public int indentationWidth = 4;
    public int indentationLvl = 0;

    public Element withIndentationLvl(int lvl) {
        indentationLvl = lvl;
        return this;
    }

    public String indent(String str) {
        return " ".repeat(indentationLvl * indentationWidth) + str;
    }
//
//    /**
//     * Serializing to string with provided indent.
//     *
//     * @param indentWidth Indent.
//     * @return Serialized string.
//     */
//    public abstract String toString(int indentWidth);
}
