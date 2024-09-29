package org.example;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void newRoundTest() {
        Game game = new Game();

        game.getPlayer().peekCard(new Card(Suit.Clubs, CardType.Ace));
        game.getDealer().peekCard(new Card(Suit.Clubs, CardType.Two));
        game.checkWinCondition(true);

        game.newRound();

        Assertions.assertEquals(game.getCurrentRound(), 2);
        Assertions.assertEquals(game.winState, WinState.NEITHER);
        Assertions.assertEquals(game.scoreTable.dealerScore, 0);
        Assertions.assertEquals(game.scoreTable.playerScore, 1);
    }
}
