import org.example.Blockquote;
import org.example.Blockquote.Builder;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Blockquote test class.
 */
public class BlockquoteTests {

    @Test
    public void emptyBlockquoteTest() {
        Blockquote.Builder empty = new Builder().withIndent(1);
        Assertions.assertEquals(">", empty.build().toString());
    }

    @Test
    public void singleBlockquoteTest() {
        Blockquote.Builder bq1 = new Builder()
            .withContent(new Text("Blockquote 1")).withIndent(1);
        Assertions.assertEquals("> Blockquote 1", bq1.build().toString());

        Blockquote.Builder bq2 = new Builder().withContent(
                new Text.Bold(new Text.Italic("Blockquote 2")))
            .withIndent(1);
        Assertions.assertEquals("> **_Blockquote 2_**", bq2.build().toString());
    }

    @Test
    public void nestedBlockquoteTest() {
        for (int i = 1; i < 100; i++) {
            Blockquote.Builder bb = new Builder().withIndent(i)
                .withContent(new Text("Blockquote " + i));
            Assertions.assertEquals(">".repeat(i) + " Blockquote " + i, bb.build().toString());
        }
    }

    @Test
    public void multiparagraphText() {
        String text =
            "Hello!\nI'm a multiline blockquote\nThat should be\nConverted\n(42)\nTo markdown";
        StringBuilder sb = new StringBuilder();
        var strings = text.split("\n");
        for (int i = 0; i < strings.length; i++) {
            Blockquote.Builder bb = new Blockquote.Builder()
                .withContent(new Text(strings[i]))
                .withIndent(1);
            sb.append(bb.build()).append("\n");
        }
        String expected = """
            > Hello!
            > I'm a multiline blockquote
            > That should be
            > Converted
            > (42)
            > To markdown""";
        Assertions.assertEquals(expected, sb.toString().stripTrailing());
    }

    @Test
    public void multilineTest() {
        String text
            = "Hello!\nI'm a multiline blockquote\nThat should be\nConverted\n(42)\nTo markdown";
        Blockquote.Builder bb = new Builder().withContent(new Text(text)).withIndent(1);
        String expected = """
            > Hello!
            >
            > I'm a multiline blockquote
            >
            > That should be
            >
            > Converted
            >
            > (42)
            >
            > To markdown""";
        Assertions.assertEquals(expected, bb.build().toString());
    }

    @Test
    public void quotesEqTest() {
        Blockquote.Builder bq1 = new Builder().withIndent(1)
            .withContent(new Text("Blockquote 1"));
        Assertions.assertEquals("> Blockquote 1", bq1.build().toString());

        Blockquote.Builder bq2 = new Builder().withIndent(1)
            .withContent(new Text.Bold(new Text.Italic("Blockquote 2")));
        Assertions.assertEquals("> **_Blockquote 2_**", bq2.build().toString());

        Assertions.assertEquals("> **_Blockquote 2_**", bq2.build().toString());

        Blockquote.Builder bq3 = new Builder().withIndent(1)
            .withContent(new Text.Bold(new Text.Italic("Blockquote 2")));
        Assertions.assertNotEquals(bq1.build(), bq2.build());
        Assertions.assertEquals(bq2.build(), bq3.build());
    }

    @Test
    public void differentIndentsEqTest() {
        Blockquote.Builder bq1 = new Builder().withIndent(1)
            .withContent(new Text("Blockquote 1"));
        Assertions.assertEquals("> Blockquote 1", bq1.build().toString());

        Blockquote.Builder bq2 = new Builder().withIndent(2)
            .withContent(new Text("Blockquote 1"));

        Assertions.assertNotEquals(bq1.build(), bq2.build());
    }
}
