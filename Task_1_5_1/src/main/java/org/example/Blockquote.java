package org.example;

public class Blockquote extends Element {

    public static class Builder {

        private final Blockquote blockquote = new Blockquote();

        public Builder withContent(Element content) {
            blockquote.content = content;
            return this;
        }

        public Builder withIndent(int indentLvl) {
            blockquote.indentLevel = indentLvl;
            return this;
        }

        public Blockquote build() {
            return blockquote;
        }
    }

    private Element content;
    private int indentLevel = 1;

    @Override
    public String toString(int indentLvl) {
        return toString();
    }

    @Override
    public String toString() {
        return ">".repeat(indentLevel) + ((content != null) ? " " + content.toString() : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blockquote another) {
            return content == another.content && indentLevel == another.indentLevel;
        }
        return false;
    }
}
