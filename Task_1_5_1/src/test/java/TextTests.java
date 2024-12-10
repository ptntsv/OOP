import org.example.Text;
import org.example.Text.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextTests {

    @Test
    public void nothingTest() {
        Text nothing = new Text.Builder().withContent("nothing").build();
        Assertions.assertEquals("nothing", nothing.toString());
    }

    @Test
    public void boldTest() {
        Builder boldBuilder = new Text.Builder().withContent("hello").bold();
        Assertions.assertEquals("**hello**", boldBuilder.build().toString());
    }

    @Test
    public void italicTest() {
        Builder italicBuilder = new Text.Builder().withContent("hello").italic();
        Assertions.assertEquals("_hello_", italicBuilder.build().toString());
    }

    @Test
    public void monospacedTest() {
        Builder monospacedBuilder = new Text.Builder().withContent("hello")
            .monospaced();
        Assertions.assertEquals("`hello`", monospacedBuilder.build().toString());
    }

    @Test
    public void strikethroughTest() {
        Builder strikethroughBuild = new Text.Builder().withContent("hello")
            .strikeThrough();
        Assertions.assertEquals("~~hello~~", strikethroughBuild.build().toString());
    }

    // TODO: make mixed tests
}
