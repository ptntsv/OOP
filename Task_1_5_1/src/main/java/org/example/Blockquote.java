package org.example;

/**
 * Represents Markdown's blockquote element.
 */
public class Blockquote extends Element {

    public static class Builder {

        private final Blockquote blockquote = new Blockquote();

        public Builder withContent(Element content) {
            blockquote.content = content;
            return this;
        }

        public Builder withIndent(int indentLvl) {
            blockquote.quoteLevel = indentLvl;
            return this;
        }

        public Blockquote build() {
            return blockquote;
        }
    }

    /**
     * Blockquote content.
     */
    private Element content;
    /**
     * Blockquote level.
     */
    private int quoteLevel = 1;

    @Override
    public String toString() {
        return ">".repeat(quoteLevel) + ((content != null) ? " " + content.toString() : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blockquote another) {
            return content.equals(another.content) && quoteLevel == another.quoteLevel;
        }
        return false;
    }
}
