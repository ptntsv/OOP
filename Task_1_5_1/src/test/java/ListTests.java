import org.example.Link;
import org.example.List;
import org.example.List.Builder;
import org.example.ListTypes;
import org.example.Text;
import org.example.UnorderedListSigns;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//
//public class ListTests {
//
//    @Test
//    public void orderedTest() {
//        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
//        innerBuilder.addItem(new Text.Builder().withContent("first").build());
//        innerBuilder.addItem(new Text.Builder().withContent("second").build());
//        innerBuilder.addItem(new Text.Builder().withContent("third").build());
//        innerBuilder.addItem(new Text.Builder().withContent("fourth").build());
//
//        String expected = """
//            1. first
//            2. second
//            3. third
//            4. fourth
//            """;
//
//        Assertions.assertEquals(expected, innerBuilder.build().toString());
//    }
//
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
//
//    @Test
//    public void orderedNestedTest() {
//        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
//        innerBuilder.addItem(new Text.Builder().withContent("first").build());
//
//        Builder firstNested = new Builder().withOrder(ListTypes.ORDERED);
//        firstNested.addItem(new Text.Builder().withContent("first first").build());
//        firstNested.addItem(new Text.Builder().withContent("first second").build());
//        firstNested.addItem(new Text.Builder().withContent("first third").build());
//
//        innerBuilder.addItem(firstNested.build());
//        innerBuilder.addItem(new Text.Builder().withContent("second").build());
//        innerBuilder.addItem(new Text.Builder().withContent("third").build());
//
//        String expected = """
//            1. first
//                1. first first
//                2. first second
//                3. first third
//            2. second
//            3. third
//            """;
//        Assertions.assertEquals(expected, innerBuilder.build().toString());
//    }
//
//    @Test
//    public void unorderedTest() {
//        Builder innerBuilder = new Builder().withOrder(ListTypes.UNORDERED)
//            .withSign(UnorderedListSigns.DASH);
//
//        Builder firstNested = new Builder().withOrder(ListTypes.UNORDERED)
//            .withSign(UnorderedListSigns.STAR);
//        firstNested.addItem(new Text.Builder().withContent("first first").build());
//
//        Builder secondNested = new Builder().withOrder(ListTypes.UNORDERED)
//            .withSign(UnorderedListSigns.PLUS);
//        secondNested.addItem(new Text.Builder().withContent("second first").build());
//
//        Builder emptyNested = new Builder().withOrder(ListTypes.UNORDERED)
//            .withSign(UnorderedListSigns.PLUS);
//        emptyNested.addItem(new Text.Builder().withContent("empty first").build());
//
//        innerBuilder.addItem(new Text.Builder().withContent("first").build());
//        innerBuilder.addItem(firstNested.build());
//        innerBuilder.addItem(new Text.Builder().withContent("second").build());
//        innerBuilder.addItem(secondNested.build());
//        innerBuilder.addItem(new Text.Builder().withContent("third").build());
//        innerBuilder.addItem(new Text.Builder().build());
//        innerBuilder.addItem(emptyNested.build());
//
//        String expected = """
//            - first
//                * first first
//            - second
//                + second first
//            - third
//            -\s
//                + empty first
//            """;
//
//        Assertions.assertEquals(expected, innerBuilder.build().toString());
//    }
//
//    @Test
//    public void fruitsAndVegsTest() {
//        Builder innerBuilder = new Builder().withOrder(ListTypes.ORDERED);
//
//        innerBuilder.addItem(new Text.Builder().withContent("fruits").bold().build());
//        Builder fruits = new Builder().withOrder(ListTypes.ORDERED);
//        fruits.addItem(new Text.Builder().withContent("apple").build());
//        fruits.addItem(new Text.Builder().withContent("banana").build());
//        innerBuilder.addItem(fruits.build());
//
//        innerBuilder.addItem(new Text.Builder().withContent("veggies").italic().build());
//        Builder veggies = new Builder().withOrder(ListTypes.ORDERED)
//            .withSign(UnorderedListSigns.PLUS);
//        veggies.addItem(new Text.Builder().withContent("green").build());
//        Builder green = new Builder().withOrder(ListTypes.ORDERED);
//        green.addItem(new Text.Builder().withContent("broccoli").build());
//        green.addItem(new Text.Builder().withContent("cucumber").build());
//        veggies.addItem(green.build());
//
//        veggies.addItem(new Text.Builder().withContent("orange").build());
//        Builder orange = new Builder().withOrder(ListTypes.ORDERED);
//        orange.addItem(new Text.Builder().withContent("carrot").build());
//        veggies.addItem(orange.build());
//
//        veggies.addItem(new Text.Builder().withContent("some dashes").build());
//        Builder nested = new Builder().withOrder(ListTypes.UNORDERED)
//            .withSign(UnorderedListSigns.DASH);
//        nested.addItem(new Text.Builder().withContent("dash 1").build());
//        nested.addItem(new Text.Builder().withContent("dash 2").build());
//        nested.addItem(new Text.Builder().withContent("dash 3").build());
//        veggies.addItem(nested.build());
//
//        String expected = """
//            1. **fruits**
//                1. apple
//                2. banana
//            2. _veggies_
//                1. green
//                    1. broccoli
//                    2. cucumber
//                2. orange
//                    1. carrot
//                3. some dashes
//                    - dash 1
//                    - dash 2
//                    - dash 3
//            """;
//
//        innerBuilder.addItem(veggies.build());
//        Assertions.assertEquals(expected, innerBuilder.build().toString());
//    }
//}
