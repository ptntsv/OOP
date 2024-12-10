import org.example.Blockquote;
import org.example.Blockquote.Builder;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//public class BlockquoteTests {
//
//    @Test
//    public void emptyBlockquoteTest() {
//        Blockquote.Builder empty = new Builder().withIndent(1);
//        Assertions.assertEquals(">", empty.build().toString());
//    }
//
//    @Test
//    public void singleBlockquoteTest() {
//        Blockquote.Builder bq1 = new Builder().withIndent(1)
//            .withContent(new Text.Builder().withContent("Blockquote 1").build());
//        Assertions.assertEquals("> Blockquote 1", bq1.build().toString());
//
//        Blockquote.Builder bq2 = new Builder().withIndent(1)
//            .withContent(new Text.Builder().withContent("Blockquote 2").bold().italic().build());
//        Assertions.assertEquals("> **_Blockquote 2_**", bq2.build().toString());
//    }
//
//    @Test
//    public void nestedBlockquoteTest() {
//        Blockquote.Builder bq1 = new Builder().withIndent(1)
//            .withContent(new Text.Builder().withContent("Blockquote 1").build());
//        Assertions.assertEquals("> Blockquote 1", bq1.build().toString());
//
//        Blockquote.Builder bq2 = new Builder().withIndent(2)
//            .withContent(new Text.Builder().withContent("Blockquote 2").build());
//        Assertions.assertEquals(">> Blockquote 2", bq2.build().toString());
//
//        for (int i = 1; i < 100; i++) {
//            Blockquote.Builder bb = new Builder().withIndent(i)
//                .withContent(new Text.Builder().withContent("Blockquote " + i).build());
//            Assertions.assertEquals(">".repeat(i) + " Blockquote " + i, bb.build().toString());
//        }
//    }
//
//    @Test
//    public void multiLineTest() {
//        String text = "Hello!\nI'm a multiline blockquote\nThat should be\nConverted\n(42)\nTo markdown";
//        StringBuilder sb = new StringBuilder();
//        var strings = text.split("\n");
//        for (int i = 0; i < strings.length; i++) {
//            Blockquote.Builder bb = new Blockquote.Builder()
//                .withContent(new Text.Builder().withContent(strings[i]).build())
//                .withIndent(1);
//            sb.append(bb.build()).append("\n");
//            if (i != strings.length - 1) {
//                sb.append(new Blockquote.Builder().withIndent(1).build()).append("\n");
//            }
//        }
//        String expected = """
//            > Hello!
//            >
//            > I'm a multiline blockquote
//            >
//            > That should be
//            >
//            > Converted
//            >
//            > (42)
//            >
//            > To markdown
//            """;
//        Assertions.assertEquals(expected, sb.toString());
//    }
//}
