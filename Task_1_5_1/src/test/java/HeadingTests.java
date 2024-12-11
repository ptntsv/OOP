import org.example.BadHeadingLevelException;
import org.example.Heading;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Heading test class.
 */
public class HeadingTests {

    @Test
    public void headingTests() {
        for (int i = 1; i <= 6; i++) {
            try {
                var heading = new Heading.Builder()
                    .withContent(new Text("Heading " + i))
                    .withLevel(i).build();
                Assertions.assertEquals("#".repeat(i) + " " + "Heading " + i, heading.toString());
            } catch (BadHeadingLevelException e) {
                throw new RuntimeException(e.getMsg());
            }
        }
    }

    @Test
    public void badHeadingTest() {
        Assertions.assertThrows(BadHeadingLevelException.class,
            () -> new Heading.Builder()
                .withContent(new Text("Heading 7"))
                .withLevel(7).build());
    }

    @Test
    public void headingEqTest() throws BadHeadingLevelException {
        var h1 = new Heading.Builder()
            .withContent(new Text("Heading 1"))
            .withLevel(1).build();

        var h1SameContent = new Heading.Builder()
            .withContent(new Text("Heading 1"))
            .withLevel(2).build();

        var h1SameIndent = new Heading.Builder()
            .withContent(new Text("Heading 1 copy"))
            .withLevel(1).build();

        var h1Same = new Heading.Builder()
            .withContent(new Text("Heading 1"))
            .withLevel(1).build();

        Assertions.assertNotEquals(h1, h1SameContent);
        Assertions.assertNotEquals(h1, h1SameIndent);
        Assertions.assertEquals(h1, h1Same);
    }
}
