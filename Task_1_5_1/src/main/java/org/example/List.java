package org.example;

import java.util.ArrayList;

public class List extends Element {


    public static class Builder {

        private final List list = new List();

        public void addItem(Element toAdd) {
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

        public List build() {
            return this.list;
        }
    }

    private final java.util.List<Element> lists = new ArrayList<>();
    private ListTypes listType;
    private char defaultChar;

    private String buildPrefix(int i) {
        return switch (listType) {
            case ORDERED -> i + ".";
            case UNORDERED -> String.valueOf(defaultChar);
        } + " ";
    }

    private String format(String str, int i, int indent) {
        return (buildPrefix(i) + str).indent(indent);
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (var el : lists) {
            if (el instanceof List) {
                sb.append(el.toString(indent + 4));
            } else {
                sb.append(format(el.toString(indent), i++, indent));
            }
        }
        return sb.toString();
    }
}
