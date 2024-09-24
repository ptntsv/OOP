import org.example.Card;
import org.example.CardType;
import org.example.Dealer;
import org.example.Game;
import org.example.InvalidTurnException;
import org.example.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    public void dealerGetCardsMsgTest() {
        var d = new Dealer();
        d.peekCard(new Card(Suit.Clubs, CardType.Ace));
        Card c = new Card(Suit.Diamonds, CardType.Four);
        c.isOpen = false;
        d.peekCard(c);
        Assertions.assertEquals(d.getCardsMsg(),
            "\tКарты дилера: [Туз Трефы (11), <закрытая карта>]");
    }

    @Test
    public void dealCardsTest() throws InvalidTurnException {
        Game game = new Game();

        var dealer = game.getDealer();
        var player = game.getPlayer();
        dealer.dealCards(game.getDeck(), player);

        Assertions.assertEquals(dealer.getCardsLen(), 2);
        Assertions.assertEquals(player.getCardsLen(), 2);

        Assertions.assertTrue(dealer.getCard(0).isOpen);
        Assertions.assertFalse(dealer.getCard(1).isOpen);

        Assertions.assertTrue(player.getCard(0).isOpen);
        Assertions.assertTrue(player.getCard(1).isOpen);
    }
    @Test
    public void dealerTakesFirstTurn() throws InvalidTurnException {
        Game game = new Game();
        var dealer = game.getDealer();
        var player = game.getPlayer();
        dealer.dealCards(game.getDeck(), player);
        Assertions.assertFalse(dealer.getHasOpened());
        dealer.takeTurn(game.getDeck(), game);
        Assertions.assertTrue(dealer.getHasOpened());
    }
}