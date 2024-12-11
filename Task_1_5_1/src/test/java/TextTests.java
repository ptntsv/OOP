import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextTests {

    @Test
    public void nothingTest() {
        Text nothing = new Text("nothing");
        Assertions.assertEquals("nothing", nothing.toString());
    }

    @Test
    public void boldTest() {
        Text bold = new Text.Bold("hello").getText();
        Assertions.assertEquals("**hello**", bold.toString());
    }

    @Test
    public void nestedBoldTest() {
        Text boldItalic = new Text.Italic(new Text.Bold("hello").getText()).getText();
        Assertions.assertEquals("**_hello_**", boldItalic.toString());
    }

    @Test
    public void italicTest() {
        Text italic = new Text.Italic("hello").getText();
        Assertions.assertEquals("_hello_", italic.toString());
    }

    @Test
    public void monospacedTest() {
        Text monospaced = new Text.Monospaced("hello").getText();
        Assertions.assertEquals("`hello`", monospaced.toString());
    }

    @Test
    public void strikedTest() {
        Text striked = new Text.Strikethrough("hello").getText();
        Assertions.assertEquals("~~hello~~", striked.toString());
    }

    @Test
    public void fullModifiedTest() {
        Text full = new Text.Bold(new Text.Italic(new Text.Monospaced(
            new Text.Strikethrough("hello").getText()).getText()).getText()).getText();
        Assertions.assertEquals("**_`~~hello~~`_**", full.toString());
    }

    @Test
    public void eqTest1() {
        Text italic = new Text.Strikethrough("hello").getText();
        Text monospaced = new Text.Monospaced("hello").getText();
        Assertions.assertNotEquals(italic, monospaced);
    }

    @Test
    public void eqTest2() {
        Text t1 = new Text.Strikethrough(new Text.Bold("hello").getText()).getText();
        Text t2 = new Text.Bold(new Text.Strikethrough("hello").getText()).getText();
        Assertions.assertEquals(t1, t2);
    }
}
