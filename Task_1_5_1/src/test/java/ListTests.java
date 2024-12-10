import org.example.Link;
import org.example.List;
import org.example.List.Builder;
import org.example.ListTypes;
import org.example.Text;
import org.example.UnorderedListSigns;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ListTests {

    @Test
    public void orderedTest() {
        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
        innerBuilder.addItem(new Text("first"));
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));
        innerBuilder.addItem(new Text("fourth"));

        String expected = """
            1. first
            2. second
            3. third
            4. fourth
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());
    }

    @Test
    public void orderedIndentedTest() {
        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
        innerBuilder.addItem(new Text("first"));

        List.Builder indented = new Builder().withOrder(ListTypes.ORDERED).asIndented();
        indented.addItem(new Text("first indented"));
        indented.addItem(new Text("second indented"));
        indented.addItem(new Text("third indented"));

        innerBuilder.addItem(indented.build());
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));
        innerBuilder.addItem(new Text("fourth"));

        String expected = """
            1. first
                1. first indented
                2. second indented
                3. third indented
            2. second
            3. third
            4. fourth
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());
    }

//    @Test
//    public void listWithLinks() {
//        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
//        innerBuilder.addItem(new Text.Builder().withContent("first").build());
//
//        Builder firstNested = new Builder().withOrder(ListTypes.ORDERED);
//        firstNested.addItem(
//            new Link.Builder().withText(new Text.Builder().withContent("Google").build())
//                .withUrl("https://google.com").build());
//        firstNested.addItem(
//            new Link.Builder().withText(new Text.Builder().withContent("Duck Duck Go").build())
//                .withUrl("https://duckduckgo.com").build());
//        firstNested.addItem(
//            new Link.Builder().withText(new Text.Builder().withContent("Yandex").build())
//                .withUrl("https://yandex.ru").build());
//
//        innerBuilder.addItem(firstNested.build());
//        innerBuilder.addItem(new Text.Builder().withContent("second").build());
//        innerBuilder.addItem(new Text.Builder().withContent("third").build());
//
//        String expected = """
//            1. first
//                1. [Google](https://google.com)
//                2. [Duck Duck Go](https://duckduckgo.com)
//                3. [Yandex](https://yandex.ru)
//            2. second
//            3. third
//            """;
//        Assertions.assertEquals(expected, innerBuilder.build().toString());
//    }

    @Test
    public void orderedNestedTest() {
        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
        innerBuilder.addItem(new Text("first"));

        Builder firstNested = new Builder().withOrder(ListTypes.ORDERED);
        firstNested.addItem(new Text("first first"));
        firstNested.addItem(new Text("first second"));
        firstNested.addItem(new Text("first third"));

        innerBuilder.addItem(firstNested.build());
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));

        String expected = """
            1. first
            2.  1. first first
                2. first second
                3. first third
            3. second
            4. third
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());
    }
}
