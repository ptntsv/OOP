import org.example.Card;
import org.example.CardType;
import org.example.Game;
import org.example.Suit;
import org.example.WinState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for proper winner choosing.
 */
public class WinnerTest {

    @Test
    public void testPlayerBlackJack() {
        Game game = new Game();
        // Player's init cards
        Card p1 = new Card(Suit.Spades, CardType.Queen);
        Card p2 = new Card(Suit.Spades, CardType.Ace);
        game.getPlayer().peekCard(p1);
        game.getPlayer().peekCard(p2);

        // Dealer's init cards
        Card d1 = new Card(Suit.Spades, CardType.Nine);
        Card d2 = new Card(Suit.Spades, CardType.Ace);
        game.getDealer().peekCard(d1);
        game.getDealer().peekCard(d2);

        game.checkWinCondition(false);
        Assertions.assertEquals(game.winState, WinState.PLAYER_WON);
    }

    @Test
    public void testNeitherThenPlayer() {
        Game game = new Game();
        // Player's init cards
        Card p1 = new Card(Suit.Spades, CardType.Four);
        Card p2 = new Card(Suit.Spades, CardType.Ace);
        game.getPlayer().peekCard(p1);
        game.getPlayer().peekCard(p2);

        // Dealer's init cards
        Card d1 = new Card(Suit.Spades, CardType.Nine);
        Card d2 = new Card(Suit.Spades, CardType.Two);
        game.getDealer().peekCard(d1);
        game.getDealer().peekCard(d2);

        game.checkWinCondition(false);
        Assertions.assertEquals(game.winState, WinState.NEITHER);
        game.getPlayer().peekCard(new Card(Suit.Clubs, CardType.Four));

        game.checkWinCondition(true);
        Assertions.assertEquals(game.winState, WinState.PLAYER_WON);
    }

    @Test
    public void testDraw() {
        Game game = new Game();
        // Player's init cards
        Card p1 = new Card(Suit.Spades, CardType.Four);
        Card p2 = new Card(Suit.Spades, CardType.Ace);
        game.getPlayer().peekCard(p1);
        game.getPlayer().peekCard(p2);

        game.getDealer().peekCard(p1);
        game.getDealer().peekCard(p2);

        game.checkWinCondition(false);
        Assertions.assertEquals(game.winState, WinState.NEITHER);

        game.checkWinCondition(true);
        Assertions.assertEquals(game.winState, WinState.DRAW);
    }

    @Test
    public void testDealer() {
        Game game = new Game();
        // Player's init cards
        Card p1 = new Card(Suit.Spades, CardType.Two);
        Card p2 = new Card(Suit.Spades, CardType.Four);
        game.getPlayer().peekCard(p1);
        game.getPlayer().peekCard(p2);
        game.getPlayer().peekCard(new Card(Suit.Clubs, CardType.Queen));

        game.getDealer().peekCard(p1);
        game.getDealer().peekCard(p2);
        game.getDealer().peekCard(new Card(Suit.Clubs, CardType.Ace));

        game.checkWinCondition(false);
        Assertions.assertEquals(game.winState, WinState.NEITHER);

        game.checkWinCondition(true);
        Assertions.assertEquals(game.winState, WinState.DEALER_WON);
    }
}