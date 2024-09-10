package org.example;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> cards = new ArrayList<>();

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        this.score += card.value;
    }

    public void reset() {
        this.cards.clear();
        this.score = 0;
    }

    public String cardsToString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            s.append(cards.get(i).toString());
            if (i != cards.size() - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
