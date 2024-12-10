import org.example.Link;
import org.example.Link.Builder;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkTests {

    @Test
    public void commonTest() {
        Link.Builder lb = new Builder().withText(new Text("Test"))
            .withUrl("https://google.com");
        Assertions.assertEquals("[Test](https://google.com)", lb.build().toString());
    }
}
