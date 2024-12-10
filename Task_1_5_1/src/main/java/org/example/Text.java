package org.example;

import java.util.EnumSet;

public class Text extends Element {

    public static class Bold extends AbstractTextModifier {

        public Bold(String content) {
            super(content);
        }

        public Bold(Text text) {
            super(text);
        }

        @Override
        public void decorate() {
            this.text.modifiers.add(TextModifiers.BOLD);
        }
    }

    public static class Italic extends AbstractTextModifier {

        public Italic(String content) {
            super(content);
        }

        public Italic(Text text) {
            super(text);
        }

        @Override
        public void decorate() {
            this.text.modifiers.add(TextModifiers.ITALIC);
        }
    }

    public static class Monospaced extends AbstractTextModifier {

        public Monospaced(String content) {
            super(content);
        }

        public Monospaced(Text text) {
            super(text);
        }

        @Override
        public void decorate() {
            this.text.modifiers.add(TextModifiers.MONOSPACED);
        }
    }

    public static class Strikethrough extends AbstractTextModifier {

        public Strikethrough(String content) {
            super(content);
        }

        public Strikethrough(Text text) {
            super(text);
        }

        @Override
        public void decorate() {
            this.text.modifiers.add(TextModifiers.STRIKETHROUGH);
        }
    }

    String content = "";
    private final EnumSet<TextModifiers> modifiers = EnumSet.of(TextModifiers.PLAIN);

    private StringBuilder format() {
        StringBuilder mods = new StringBuilder();
        if (modifiers.contains(TextModifiers.BOLD)) {
            mods.append("**");
        }
        if (modifiers.contains(TextModifiers.ITALIC)) {
            mods.append("_");
        }
        if (modifiers.contains(TextModifiers.MONOSPACED)) {
            mods.append("`");
        }
        if (modifiers.contains(TextModifiers.STRIKETHROUGH)) {
            mods.append("~~");
        }
        return mods;
    }

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return indent(format() + this.content + format().reverse());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Text another) {
            return another.toString().equals(toString());
        }
        return false;
    }
}