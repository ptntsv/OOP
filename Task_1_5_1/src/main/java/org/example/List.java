package org.example;

import java.util.ArrayList;

public class List extends Element {


    public static class Builder {

        private final List list = new List();

        public void addItem(Element toAdd) {
            if (toAdd instanceof List list) {
                list.indentationLvl += 1;
            }
            list.lists.add(toAdd);
        }

        public Builder withOrder(ListTypes listType) {
            list.listType = listType;
            return this;
        }

        public Builder withSign(UnorderedListSigns sign) {
            list.defaultChar = sign.getChar();
            return this;
        }

        public Builder asIndented() {
            list.asIndented = true;
            return this;
        }

        public List build() {
            return this.list;
        }
    }

    private final java.util.List<Element> lists = new ArrayList<>();
    private ListTypes listType;
    private char defaultChar;
    private boolean asIndented = false;

    private String buildPrefix(int i) {
        return switch (listType) {
            case ORDERED -> i + ".";
            case UNORDERED -> String.valueOf(defaultChar);
        } + " ";
    }

    private String format(String str, int i) {
        return indent(buildPrefix(i) + str);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (var el : lists) {
            String str = el.toString().stripTrailing();
            if (el instanceof List list) {
                if (list.asIndented) {
                    sb.append(str).append("\n");
                    continue;
                }
                str = str.substring(buildPrefix(i).length());
            }
            sb.append(format(str, i++)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof List another) {
            return lists.equals(another.lists);
        }
        return false;
    }
}
