package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Markdown's blockquote element.
 */
public class Blockquote extends Element {

    /**
     * Quote builder.
     */
    public static class Builder {

        private final Blockquote blockquote = new Blockquote();

        public Builder asMultiline() {
            blockquote.asMultiline = true;
            return this;
        }

        public void addItem(Element element) {
            blockquote.elements.add(element);
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
    private final List<Element> elements = new ArrayList<>();
    /**
     * Blockquote level.
     */
    private int quoteLevel = 1;

    /**
     * Either blockquote is multilined or not.
     */
    private boolean asMultiline = false;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (elements.isEmpty()) {
            sb.append(">");
            return sb.toString();
        }
        for (int i = 0; i < elements.size(); i++) {
            sb.append(">".repeat(quoteLevel)).append(" ").append(elements.get(i));
            if (i < elements.size() - 1 && asMultiline) {
                sb.append("\n>\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blockquote another) {
            return elements.equals(another.elements) && quoteLevel == another.quoteLevel;
        }
        return false;
    }
}
