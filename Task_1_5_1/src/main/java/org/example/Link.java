package org.example;

/**
 * Represents Markdown's link element.
 */
public class Link extends Element {

    public static class Builder {

        private final Link link = new Link();

        /**
         * Specifies link content (part inside brackets).
         *
         * @param content Content.
         * @return New builder instance.
         */
        public Link.Builder withContent(Element content) {
            link.content = content;
            return this;
        }

        /**
         * Specifies link URL (part inside parenthesis).
         *
         * @param url URL.
         * @return New builder instance.
         */
        public Link.Builder withUrl(String url) {
            link.url = url;
            return this;
        }

        /**
         * Specifies link title.
         *
         * @param title Title.
         * @return New builder instance.
         */
        public Link.Builder withTitle(String title) {
            link.title = title;
            return this;
        }

        public Link build() {
            return link;
        }
    }

    /**
     * Part inside brackets.
     */
    private Element content;
    /**
     * Part inside parenthesis.
     */
    private String url = "";
    /**
     * Part after URL.
     */
    private String title = "";

    /**
     * Surrounds link text with brackets.
     *
     * @return Surrounded text.
     */
    private String buildText() {
        return "[" + content + "]";
    }

    /**
     * Surrounds link URL and title with parens.
     *
     * @return Surrounded URL.
     */
    private String buildUrl() {
        return "(" + url + (!title.isEmpty() ? (" \"" + title + "\"") : "") + ")";
    }

    @Override
    public String toString() {
        return indent(buildText() + buildUrl());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Link another) {
            return content.equals(another.content) && url.equals(another.url) && title.equals(
                another.title);
        }
        return false;
    }
}
