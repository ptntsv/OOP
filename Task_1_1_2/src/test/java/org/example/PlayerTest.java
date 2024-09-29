package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void getCardsMsgMiscTest() {
        var p = new Player();
        p.peekCard(new Card(Suit.Clubs, CardType.Ace));
        p.peekCard(new Card(Suit.Hearts, CardType.Two));
        p.peekCard(new Card(Suit.Diamonds, CardType.Four));
        Assertions.assertEquals(p.getCardsMsg(),
            "\tВаши карты: [Туз Трефы (11), Двойка Червы (2), Четверка Бубны (4)] => 17");
    }

    @Test
    public void getCardsMsgAcesTest() {
        var p = new Player();
        p.peekCard(new Card(Suit.Clubs, CardType.Ace));
        p.peekCard(new Card(Suit.Diamonds, CardType.Four));
        p.peekCard(new Card(Suit.Hearts, CardType.Ace));
        Assertions.assertEquals(p.getCardsMsg(),
            "\tВаши карты: [Туз Трефы (1), Четверка Бубны (4), Туз Червы (1)] => 6");
    }
}