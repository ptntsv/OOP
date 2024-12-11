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
        Builder innerBuilder = new Builder().withType(ListTypes.ORDERED);
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
    public void unorderedTest() {
        Builder innerBuilder = new Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.STAR);
        innerBuilder.addItem(new Text("first"));
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));
        innerBuilder.addItem(new Text("fourth"));

        String expected = """
            * first
            * second
            * third
            * fourth
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());

    }

    @Test
    public void orderedIndentedTest() {
        Builder innerBuilder = new Builder().withType(ListTypes.ORDERED);
        innerBuilder.addItem(new Text("first"));

        List.Builder indented = new Builder().withType(ListTypes.ORDERED).asIndented();
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

    @Test
    public void unorderedIndentedTest() {
        Builder innerBuilder = new Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.STAR);
        innerBuilder.addItem(new Text("first"));

        List.Builder indented = new Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.DASH).asIndented();
        indented.addItem(new Text("first indented"));
        indented.addItem(new Text("second indented"));
        indented.addItem(new Text("third indented"));

        innerBuilder.addItem(indented.build());
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));
        innerBuilder.addItem(new Text("fourth"));

        String expected = """
            * first
                - first indented
                - second indented
                - third indented
            * second
            * third
            * fourth
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());
    }

    @Test
    public void orderedNestedTest() {
        Builder innerBuilder = new Builder().withType(ListTypes.ORDERED);
        innerBuilder.addItem(new Text("first"));

        Builder firstNested = new Builder().withType(ListTypes.ORDERED);
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

    @Test
    public void unorderedNestedTest() {
        Builder innerBuilder = new Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.PLUS);
        innerBuilder.addItem(new Text("first"));

        Builder firstNested = new Builder().withType(ListTypes.ORDERED);
        firstNested.addItem(new Text("first first"));
        firstNested.addItem(new Text("first second"));
        firstNested.addItem(new Text("first third"));

        innerBuilder.addItem(firstNested.build());
        innerBuilder.addItem(new Text("second"));
        innerBuilder.addItem(new Text("third"));

        String expected = """
            + first
            +   1. first first
                2. first second
                3. first third
            + second
            + third
            """;
        Assertions.assertEquals(expected, innerBuilder.build().toString());
    }

    @Test
    public void eqTest1() {
        List.Builder ordered = new Builder().withType(ListTypes.ORDERED);
        ordered.addItem(new Text("first"));
        ordered.addItem(new Text("second"));
        ordered.addItem(new Text("third"));
        ordered.addItem(new Text("fourth"));

        List.Builder ordered1 = new Builder().withType(ListTypes.ORDERED);
        ordered1.addItem(new Text("first"));
        ordered1.addItem(new Text("third"));
        ordered1.addItem(new Text("fourth"));
        ordered1.addItem(new Text("second"));

        Assertions.assertNotEquals(ordered.build(), ordered1.build());
    }

    @Test
    public void eqTest2() {
        Builder ordered = new Builder().withType(ListTypes.ORDERED);
        ordered.addItem(new Text("first"));
        ordered.addItem(new Text("second"));
        ordered.addItem(new Text("third"));
        ordered.addItem(new Text("fourth"));

        Builder unordered = new Builder().withType(ListTypes.UNORDERED)
            .withSign(UnorderedListSigns.STAR);
        unordered.addItem(new Text("first"));
        unordered.addItem(new Text("second"));
        unordered.addItem(new Text("third"));
        unordered.addItem(new Text("fourth"));

        Assertions.assertEquals(ordered.build(), unordered.build());
    }

    @Test
    public void indetedAndNestedEqTest() {
        Builder withIndented = new Builder().withType(ListTypes.ORDERED);
        withIndented.addItem(new Text("first"));

        List.Builder indented = new Builder().withType(ListTypes.ORDERED).asIndented();
        indented.addItem(new Text("first indented"));
        indented.addItem(new Text("second indented"));
        indented.addItem(new Text("third indented"));

        withIndented.addItem(indented.build());
        withIndented.addItem(new Text("second"));
        withIndented.addItem(new Text("third"));
        withIndented.addItem(new Text("fourth"));

        Builder withNested = new Builder().withType(ListTypes.ORDERED);
        withNested.addItem(new Text("first"));

        Builder firstNested = new Builder().withType(ListTypes.ORDERED);
        firstNested.addItem(new Text("first first"));
        firstNested.addItem(new Text("first second"));
        firstNested.addItem(new Text("first third"));

        withNested.addItem(firstNested.build());
        withNested.addItem(new Text("second"));
        withNested.addItem(new Text("third"));

        Assertions.assertNotEquals(withIndented.build(), withNested.build());
    }
}
