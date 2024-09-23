package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Card getCard(int index) {
        return deck.get(index);
    }

    public int getDeckLen() {
        return deck.size();
    }

    public Card peekCard(boolean isOpen) throws DeckIsEmptyException {
        if (deck.isEmpty()) {
            throw new DeckIsEmptyException("Колода пуста.");
        }
        var c = deck.remove(deck.size() - 1);
        c.isOpen = isOpen;
        return c;
    }

    public void reset() {
        deck.clear();
        for (Suit s : Suit.values()) {
            for (CardType t : CardType.values()) {
                deck.add(new Card(s, t));
            }
        }
        Collections.shuffle(deck);
    }

    public Deck() {
        reset();
    }
}
