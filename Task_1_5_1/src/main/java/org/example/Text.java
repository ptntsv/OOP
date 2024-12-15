package org.example;

/**
 * Represents Markdown's text element.
 */
public class Text extends Element {

    /**
     * Represents base text modifier.
     */
    public abstract static class AbstractTextModifier extends Text {

        /**
         * Constructor with string.
         *
         * @param content Provided string.
         */
        public AbstractTextModifier(String content) {
            this.content = content;
        }

        /**
         * Constructor with text element.
         *
         * @param text Text element.
         */
        public AbstractTextModifier(Text text) {
            this.content = text.toString();
        }
    }

    /**
     * Bold builder.
     */
    public static class Bold extends AbstractTextModifier {

        public Bold(String content) {
            super(content);
        }

        public Bold(Text text) {
            super(text);
        }

        @Override
        public String toString() {
            return "**" + content + "**";
        }
    }

    /**
     * Italic builder.
     */
    public static class Italic extends AbstractTextModifier {

        public Italic(String content) {
            super(content);
        }

        public Italic(Text text) {
            super(text);
        }

        @Override
        public String toString() {
            return '_' + content + '_';
        }
    }

    /**
     * Monospaced builder.
     */
    public static class Monospaced extends AbstractTextModifier {

        public Monospaced(String content) {
            super(content);
        }

        public Monospaced(Text text) {
            super(text);
        }

        @Override
        public String toString() {
            return '`' + content + '`';
        }
    }

    /**
     * Strikethrough builder.
     */
    public static class Strikethrough extends AbstractTextModifier {

        public Strikethrough(String content) {
            super(content);
        }

        public Strikethrough(Text text) {
            super(text);
        }

        @Override
        public String toString() {
            return "~~" + content + "~~";
        }
    }

    /**
     * Text content.
     */
    String content = "";


    /**
     * Constructor.
     */
    public Text() {
    }

    /**
     * Constructor with string.
     */
    public Text(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return indent(content);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Text another) {
            return another.toString().equals(toString());
        }
        return false;
    }
}