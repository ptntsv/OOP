import org.example.Image;
import org.example.Link;
import org.example.Link.Builder;
import org.example.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Link test class.
 */
public class LinkTests {

    @Test
    public void commonTest() {
        Link.Builder lb = new Builder().withContent(new Text("Test")).withUrl("https://google.com");
        Assertions.assertEquals("[Test](https://google.com)", lb.build().toString());
    }

    @Test
    public void boldTextTest() {
        Link.Builder lb = new Builder().withContent(new Text.Bold("Test").getText())
            .withUrl("https://google.com");
        Assertions.assertEquals("[**Test**](https://google.com)", lb.build().toString());
    }

    @Test
    public void linkWithTitleTest() {
        Link.Builder lb = new Builder().withContent(new Text("Titled")).withTitle("This is title!")
            .withUrl("https://google.com");
        Assertions.assertEquals("[Titled](https://google.com \"This is title!\")",
            lb.build().toString());
    }

    @Test
    public void linkingImagesTest() {
        Image.Builder img = new Image.Builder().withLink(
            new Link.Builder().withContent(new Text("An old rock in the desert"))
                .withUrl("/assets/images/shiprock.jpg")
                .withTitle("Shiprock, New Mexico by Beau Rogers").build());
        Link.Builder linkImg = new Link.Builder().withContent(img.build()).withUrl(
            "https://www.flickr.com/photos/beaurogers/31833779864/in/photolist-Qv3rFw-34mt9F-a9Cmfy-5Ha3Zi-9msKdv-o3hgjr-hWpUte-4WMsJ1-KUQ8N-deshUb-vssBD-6CQci6-8AFCiD-zsJWT-nNfsgB-dPDwZJ-bn9JGn-5HtSXY-6CUhAL-a4UTXB-ugPum-KUPSo-fBLNm-6CUmpy-4WMsc9-8a7D3T-83KJev-6CQ2bK-nNusHJ-a78rQH-nw3NvT-7aq2qf-8wwBso-3nNceh-ugSKP-4mh4kh-bbeeqH-a7biME-q3PtTf-brFpgb-cg38zw-bXMZc-nJPELD-f58Lmo-bXMYG-bz8AAi-bxNtNT-bXMYi-bXMY6-bXMYv");

        String expected = "[![An old rock in the desert](/assets/images/shiprock.jpg \"Shiprock, New Mexico by Beau Rogers\")](https://www.flickr.com/photos/beaurogers/31833779864/in/photolist-Qv3rFw-34mt9F-a9Cmfy-5Ha3Zi-9msKdv-o3hgjr-hWpUte-4WMsJ1-KUQ8N-deshUb-vssBD-6CQci6-8AFCiD-zsJWT-nNfsgB-dPDwZJ-bn9JGn-5HtSXY-6CUhAL-a4UTXB-ugPum-KUPSo-fBLNm-6CUmpy-4WMsc9-8a7D3T-83KJev-6CQ2bK-nNusHJ-a78rQH-nw3NvT-7aq2qf-8wwBso-3nNceh-ugSKP-4mh4kh-bbeeqH-a7biME-q3PtTf-brFpgb-cg38zw-bXMZc-nJPELD-f58Lmo-bXMYG-bz8AAi-bxNtNT-bXMYi-bXMY6-bXMYv)";
        Assertions.assertEquals(expected, linkImg.build().toString());
    }

    @Test
    public void linkEqTest() {
        Link.Builder lb1 = new Builder().withContent(new Text("Titled")).withTitle("This is title!")
            .withUrl("https://google.com");
        Link.Builder lb2 = new Builder().withContent(new Text("Test"))
            .withUrl("https://google.com");
        Link.Builder lb3 = new Builder().withContent(new Text("Test"))
            .withUrl("https://google.com");
        Assertions.assertNotEquals(lb1.build(), lb2.build());
        Assertions.assertEquals(lb2.build(), lb3.build());
    }
}
