package org.example;

public class Text extends Element {


    public static class Builder {

        private final Text text = new Text();

        private void decorate(TextModifiers modifier) {
            text.modifiers += switch (modifier) {
                case BOLD -> "**";
                case ITALIC -> "_";
                case MONOSPACED -> "`";
                case STRIKETHROUGH -> "~~";
            };
        }

        public Builder bold() {
            decorate(TextModifiers.BOLD);
            return this;
        }

        public Builder italic() {
            decorate(TextModifiers.ITALIC);
            return this;
        }

        public Builder monospaced() {
            decorate(TextModifiers.MONOSPACED);
            return this;
        }

        public Builder strikeThrough() {
            decorate(TextModifiers.STRIKETHROUGH);
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