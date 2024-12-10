package org.example;

public class Text extends Element {


    public static class Builder {

        private final Text text = new Text();

        private void format(TextModifiers modifier) {
            text.modifiers += switch (modifier) {
                case BOLD -> "**";
                case ITALIC -> "_";
                case MONOSPACED -> "`";
                case STRIKETHROUGH -> "~~";
            };
        }

        public Builder bold() {
            format(TextModifiers.BOLD);
            return this;
        }

        public Builder italic() {
            format(TextModifiers.ITALIC);
            return this;
        }

        public Builder monospaced() {
            format(TextModifiers.MONOSPACED);
            return this;
        }

        public Builder strikeThrough() {
            format(TextModifiers.STRIKETHROUGH);
            return this;
        }

        public Builder withContent(String content) {
            text.content = content;
            return this;
        }

        public Text build() {
            return text;
        }
    }

    private String content = "";
    private String modifiers = "";

    @Override
    public String toString(int indent) {
        return this.modifiers + this.content + new StringBuilder(modifiers).reverse();
    }

    @Override
    public String toString() {
        return toString(0);
    }
}