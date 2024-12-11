import org.example.Image;
import org.example.Link;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Image test class.
 */

public class ImageTests {

    @Test
    public void commonTest() {
        Image.Builder im1 = new Image.Builder().withLink(
            new Link.Builder().withContent(new Text("Test"))
                .withUrl("https://google.com").build());
        Assertions.assertEquals("![Test](https://google.com)", im1.build().toString());
    }

    @Test
    public void imagesEqTest() {
        Image.Builder im1 = new Image.Builder().withLink(
            new Link.Builder().withContent(new Text("First"))
                .withUrl("https://first.com").build());
        Image.Builder im2 = new Image.Builder().withLink(
            new Link.Builder().withContent(new Text("Second"))
                .withUrl("https://second.com").build());
        Image.Builder im1Eq = new Image.Builder().withLink(
            new Link.Builder().withContent(new Text("First"))
                .withUrl("https://first.com").build());

        Assertions.assertNotEquals(im1.build(), im2.build());
        Assertions.assertEquals(im1.build(), im1Eq.build());
    }
}
