package org.example;

public class Heading extends Element {

    public static class Builder {

        private final Heading heading = new Heading();

        public Builder withLevel(int lvl) throws BadHeadingLevelException {
            heading.setLevel(lvl);
            return this;
        }

        public Builder withContent(Text content) {
            heading.content = content;
            return this;
        }

        public Heading build() {
            return this.heading;
        }
    }

    private Text content;
    private int level = 1;

    public void setLevel(int lvl) throws BadHeadingLevelException {
        if (lvl > 6) {
            throw new BadHeadingLevelException(lvl);
        }
        level = lvl;
    }

    @Override
    public String toString(int indentLvl) {
        return "#".repeat(level) + " " + content.toString();
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Heading another) {
            return another.content.equals(content) && another.level == level;
        }
        return false;
    }
}
