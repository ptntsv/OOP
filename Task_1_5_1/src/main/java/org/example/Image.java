package org.example;

/**
 * Represents Markdown's image element.
 */
public class Image extends Element {

    public static class Builder {

        private final Image image = new Image();

        public Image.Builder withLink(Link link) {
            image.link = link;
            return this;
        }

        public Image build() {
            return image;
        }
    }

    /**
     * Link to image.
     */
    private Link link = new Link();

    @Override
    public String toString() {
        return indent("!" + link.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Image another) {
            return link.equals(another.link);
        }
        return false;
    }
}
