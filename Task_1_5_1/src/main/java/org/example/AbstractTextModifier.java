package org.example;

public abstract class AbstractTextModifier {

    protected Text text = new Text();

    public abstract void decorate();

    public Text getText() {
        return this.text;
    }

    public AbstractTextModifier(String content) {
        this.text.content = content;
        decorate();
    }

    public AbstractTextModifier(Text text) {
        this.text = text;
        decorate();
    }
}
