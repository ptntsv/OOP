import java.util.ArrayList;
import org.example.Card;
import org.example.CardType;
import org.example.Deck;
import org.example.DeckIsEmptyException;
import org.example.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for Deck class.
 */
public class DeckTest {

    /**
     * Test for initializing deck and get sure that cards in deck are shuffled.
     */
    @Test
    public void resetDeckTest() {
        var d = new Deck();
        final int expectedDeckLen = 52;
        Assertions.assertEquals(expectedDeckLen, d.getDeckLen());
        ArrayList<Card> unshuffled = new ArrayList<>();

        for (Suit s : Suit.values()) {
            for (CardType t : CardType.values()) {
                unshuffled.add(new Card(s, t));
            }
        }
        boolean flag = false;
        for (int i = 0; i < d.getDeckLen(); i++) {
            if (!d.getCard(i).equals(unshuffled.get(i))) {
                flag = true;
            }
        }
        Assertions.assertTrue(flag);
    }

    @Test
    public void peekCardTest() throws DeckIsEmptyException {
        var d = new Deck();
        int expectedLen = 52;

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
