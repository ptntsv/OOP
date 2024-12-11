package org.example;

/**
 * Suprisingly Main.
 */
public class Main {

    /**
     * Main function
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        String coolString = "Cool String";
        Text coolBoldString = new Text.Bold(coolString).getText(); // becomes like **Cool String**

        Text coolItalicString = new Text.Italic(
            coolString).getText();

        List.Builder coolList = new List.Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.DASH);

        coolList.addItem(coolBoldString);
        coolList.addItem(coolItalicString);

        String serializedCoolList = coolList.toString();

        // make with this string whatever you want.
    }
}