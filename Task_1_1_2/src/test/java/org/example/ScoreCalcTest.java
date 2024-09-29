package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests that ensure proper score calculation.
 */
public class ScoreCalcTest {

    @Test
    public void acesTest1() {
        Player p = new Player();
        var c1 = new Card(Suit.Diamonds, CardType.Ace);
        p.peekCard(c1);
        var c2 = new Card(Suit.Diamonds, CardType.Ace);
        p.peekCard(c2);
        Assertions.assertEquals(2, p.getScore());
        var c3 = new Card(Suit.Spades, CardType.Ace);
        p.peekCard(c3);
        Assertions.assertEquals(13, p.getScore());
        var c4 = new Card(Suit.Spades, CardType.Ace);
        p.peekCard(c4);
        Assertions.assertEquals(4, p.getScore());
    }

    @Test
    public void acesTest2() {
        Player p = new Player();
        var c1 = new Card(Suit.Diamonds, CardType.Nine);
        var c2 = new Card(Suit.Spades, CardType.Ace);
        p.peekCard(c1);
        p.peekCard(c2);
        Assertions.assertEquals(20, p.getScore());
        p.peekCard(new Card(Suit.Diamonds, CardType.Ace));
        Assertions.assertEquals(11, p.getScore());
    }

    @Test
    public void acesTest3() {
        Player p = new Player();
        for (int i = 0; i < 21; i++) {
            p.peekCard(new Card(Suit.Diamonds, CardType.Ace));
        }
        Assertions.assertEquals(21, p.getScore());
    }
}
