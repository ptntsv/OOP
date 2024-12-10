package org.example;

public class Heading extends Element {

    public static class Builder {

        private final Heading heading = new Heading();

        public Builder withLevel(int lvl) throws BadHeadingLevelException {
            heading.setLevel(lvl);
            return this;
        }

        public Builder withContent(String content) {
            heading.content = content;
            return this;
        }

        public Heading build() {
            return this.heading;
        }
    }

    private String content = "";
    private int level = 1;

    public void setLevel(int lvl) throws BadHeadingLevelException {
        if (lvl > 6) {
            throw new BadHeadingLevelException(lvl);
        }
        level = lvl;
    }

    @Override
    public String toString(int indentLvl) {
        return "#".repeat(level) + " " + content;
    }

    @Override
    public String toString() {
        return toString(0);
    }
}
