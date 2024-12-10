package org.example;

public class Link extends Element {

    public static class Builder {

        private final Link link = new Link();

        public Link.Builder withText(Text text) {
            link.text = text;
            return this;
        }

        public Link.Builder withUrl(String url) {
            link.url = url;
            return this;
        }

        public Link build() {
            return link;
        }
    }

    private Text text;
    private String url;

    @Override
    public String toString() {
        return indent("[" + text + "]" + "(" + url + ")");
    }
}
