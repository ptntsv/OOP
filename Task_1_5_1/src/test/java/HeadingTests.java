import org.example.BadHeadingLevelException;
import org.example.Heading;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
