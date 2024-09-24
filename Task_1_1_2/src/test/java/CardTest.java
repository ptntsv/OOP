package org.example.tests;

import org.example.Card;
import org.example.CardType;
import org.example.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for proper converting Card -> string mostly.
 */
public class CardTest {

    @Test
    public void openQueenHeartsTest() {
        var c = new Card(Suit.Hearts, CardType.Queen);
        Assertions.assertTrue(c.isOpen && c.value == 10);
        Assertions.assertEquals(c.toString(), "Дама Червы (10)");
    }

    @Test
    public void openAceSpadesTest() {
        var c = new Card(Suit.Spades, CardType.Ace);
        Assertions.assertTrue(c.isOpen && c.value == 11);
        Assertions.assertEquals(c.toString(), "Туз Пики (11)");
    }

    @Test
    public void openTwoSpadesTest() {
        var c = new Card(Suit.Spades, CardType.Two);
        Assertions.assertTrue(c.isOpen && c.value == 2);
        Assertions.assertEquals(c.toString(), "Двойка Пики (2)");
    }

    @Test
    public void openNineDiamondsTest() {
        var c = new Card(Suit.Diamonds, CardType.Nine);
        Assertions.assertTrue(c.isOpen && c.value == 9);
        Assertions.assertEquals(c.toString(), "Девятка Бубны (9)");
    }

    @Test
    public void closedTest() {
        var c = new Card(Suit.Diamonds, CardType.Nine);
        c.isOpen = false;
        Assertions.assertFalse(c.isOpen);
        Assertions.assertEquals(c.toString(), "<закрытая карта>");
    }
}
