package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    private Random rand = new Random();
    private int deckLen;

    public int getDeckLen() {
        return deckLen;
    }

    public boolean isEmpty() {
        return deckLen == 0;
    }

    public Card peekCard(boolean isOpen) {
        int index = rand.nextInt(deckLen);
        Card ret = deck.get(index);
        ret.isOpen = isOpen;
        deck.set(index, deck.get(--deckLen));
        return ret;
    }

    public void reset() {
        for (Suit s : Suit.values()) {
            for (CardType t : CardType.values()) {
                deck.add(new Card(s, t));
            }

        }
        deckLen = deck.size();
    }

    public Deck() {
        reset();
    }
}
