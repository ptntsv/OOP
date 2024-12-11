package org.example;

/**
 * Class for headings representation.
 */
public class Heading extends Element {

    /**
     * Heading builder.
     */
    public static class Builder {

        private final Heading heading = new Heading();

        /**
         * Setting up blockquote level.
         *
         * @param lvl Level.
         * @return New builder instance.
         * @throws BadHeadingLevelException if quote level is greater than 6.
         */
        public Builder withLevel(int lvl) throws BadHeadingLevelException {
            heading.setLevel(lvl);
            return this;
        }

        /**
         * Setting up blockquote content.
         *
         * @param content Content.
         * @return New builder instance.
         */
        public Builder withContent(Text content) {
            heading.content = content;
            return this;
        }

        /**
         * Get Heading instance.
         *
         * @return New Heading instance.
         */
        public Heading build() {
            return this.heading;
        }
    }

    private Text content;
    private int level = 1;

    /**
     * Setting heading level.
     *
     * @param lvl Level.
     * @throws BadHeadingLevelException if quote level is greater than 6.
     */
    public void setLevel(int lvl) throws BadHeadingLevelException {
        if (lvl > 6) {
            throw new BadHeadingLevelException(lvl);
        }
        level = lvl;
    }

    @Override
    public String toString() {
        return "#".repeat(level) + " " + content.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Heading another) {
            return another.content.equals(content) && another.level == level;
        }
        return false;
    }
}
