import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.example.Card;
import org.example.CardType;
import org.example.Game;
import org.example.InvalidTurnException;
import org.example.Suit;
import org.example.WinState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void gameInitTest() {
        Game game = new Game();
        Assertions.assertEquals(game.getCurrentRound(), 1);
        Assertions.assertEquals(game.winState, WinState.NEITHER);
        Assertions.assertEquals(game.scoreTable.dealerScore, 0);
        Assertions.assertEquals(game.scoreTable.playerScore, 0);
    }
    @Test
    public void newRoundTest()
        throws InvalidTurnException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Game game = new Game();

        game.getPlayer().peekCard(new Card(Suit.Clubs, CardType.Ace));
        game.getDealer().peekCard(new Card(Suit.Clubs, CardType.Two));
        game.checkWinCondition(true);

        Method newRoundMethod = Game.class.getDeclaredMethod("newRound");
        newRoundMethod.setAccessible(true);
        newRoundMethod.invoke(game);

        Assertions.assertEquals(game.getCurrentRound(), 2);
        Assertions.assertEquals(game.winState, WinState.NEITHER);
        Assertions.assertEquals(game.scoreTable.dealerScore, 0);
        Assertions.assertEquals(game.scoreTable.playerScore, 1);
    }
}
