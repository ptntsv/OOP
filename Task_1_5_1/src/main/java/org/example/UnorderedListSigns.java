package org.example;

public enum UnorderedListSigns {
    DASH('-'), PLUS('+'), STAR('*');

    private final char ch;

    UnorderedListSigns(char ch) {
        this.ch = ch;
    }

    public char getChar() {
        return this.ch;
    }
}
