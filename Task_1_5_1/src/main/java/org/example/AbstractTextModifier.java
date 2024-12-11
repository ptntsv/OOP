package org.example;

/**
 * Represents base text modifier.
 */
public abstract class AbstractTextModifier {

    /**
     * Text field.
     */
    protected Text text = new Text();

    /**
     * Applies modifiers to text.
     */
    public abstract void decorate();

    /**
     * Text getter.
     *
     * @return Text instance.
     */
    public Text getText() {
        return this.text;
    }

    /**
     * Constructor with string.
     *
     * @param content Provided string.
     */
    public AbstractTextModifier(String content) {
        this.text.content = content;
        decorate();
    }

    /**
     * Constructor with text element.
     *
     * @param text Text element.
     */
    public AbstractTextModifier(Text text) {
        this.text = text;
        decorate();
    }
}
