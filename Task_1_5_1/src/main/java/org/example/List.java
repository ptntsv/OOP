package org.example;

import java.util.ArrayList;

/**
 * Represents Markdown's list element.
 */

public class List extends Element {


    /**
     * List builder.
     */
    public static class Builder {

        private final List list = new List();

        /**
         * Adding element in list.
         *
         * @param toAdd Element to add.
         */
        public void addItem(Element toAdd) {
            toAdd.setIndentationLvl(toAdd.getIndentationLvl() + 1);
            list.lists.add(toAdd);
        }

        /**
         * Specifies type of list (ordered or unordered).
         *
         * @param listType Provided list type.
         * @return New list builder instance.
         */
        public Builder withType(ListTypes listType) {
            list.listType = listType;
            return this;
        }

        /**
         * Specifies unordered list mark (if list is ordered then property is ignoring).
         *
         * @param sign List mark sign.
         * @return New list builder instance.
         */
        public Builder withSign(UnorderedListSigns sign) {
            list.defaultChar = sign.getChar();
            return this;
        }

        /**
         * Specifies that list should be considered as indented (rather than nested).
         *
         * @return New list builder instance.
         */
        public Builder asIndented() {
            list.asIndented = true;
            return this;
        }

        /**
         * Gets list instance.
         *
         * @return New List instance.
         */
        public List build() {
            return this.list;
        }
    }

    /**
     * List elements.
     */
    private final java.util.List<Element> lists = new ArrayList<>();
    private ListTypes listType;
    /**
     * Character that should be used as unordered mark sign.
     */
    private char defaultChar;
    /**
     * Either list is indented or nested (by default is nested).
     */
    private boolean asIndented = false;

    /**
     * Adds numbers as prefix if list in ordered, mark sign otherwise.
     *
     * @param i Current number.
     * @return Prefix.
     */
    private String buildPrefix(int i) {
        return switch (listType) {
            case ORDERED -> i + ".";
            case UNORDERED -> String.valueOf(defaultChar);
        } + " ";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (var el : lists) {
            String elStr = el.toString();
            if (el instanceof List listEl) {
                if (listEl.asIndented) {
                    sb.append(indent(elStr)).append("\n");
                    continue;
                }
                sb.append(buildPrefix(i++))
                    .append(elStr.substring(buildPrefix(i).length()));
            } else {
                sb.append(buildPrefix(i++))
                    .append(elStr.stripLeading());
            }
            sb.append("\n");
        }
        return indent(sb.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof List another) {
            return lists.equals(another.lists) && asIndented == another.asIndented;
        }
        return false;
    }
}
