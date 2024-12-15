package org.example;

/**
 * Represents Markdown's blockquote element.
 */
public class Blockquote extends Element {

    /**
     * Quote builder.
     */
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
    private Element content = new Text("");
    /**
     * Blockquote level.
     */
    private int quoteLevel = 1;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var paragraphs = content.toString().split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            sb.append(">".repeat(quoteLevel)).append(" ").append(paragraphs[i]).append("\n");
            if (i < paragraphs.length - 1) {
                sb.append(">\n");
            }
        }
        return sb.toString().stripTrailing();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blockquote another) {
            return content.equals(another.content) && quoteLevel == another.quoteLevel;
        }
        return false;
    }
}
