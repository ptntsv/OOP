import org.example.Deck;
import org.example.DeckIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void commonTest() throws DeckIsEmptyException {
        var d = new Deck();
        int expectedLen = 52;
        Assertions.assertEquals(expectedLen, d.getDeckLen());

        Assertions.assertFalse(d.peekCard(false).isOpen);
        Assertions.assertEquals(expectedLen - 1, d.getDeckLen());

        Assertions.assertTrue(d.peekCard(true).isOpen);
        Assertions.assertEquals(expectedLen - 2, d.getDeckLen());

        d.reset();

        for (int i = 0; i < 52; i++) {
            d.peekCard(true);
        }

        Assertions.assertEquals(d.getDeckLen(), 0);
        Assertions.assertThrows(DeckIsEmptyException.class, () -> d.peekCard(true));
    }
}
