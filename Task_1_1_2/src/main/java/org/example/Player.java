package org.example;

import java.util.ArrayList;

/**
 * Blackjack player class.
 */
public class Player {

    final ArrayList<Card> cards = new ArrayList<>();

    public int getCardsLen() {
        return cards.size();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    private int score;

    public int getScore() {
        return score;
    }

    /**
     * Change all ace's values to 1.
     */
    private void downgradeAces() {
        for (var c : this.cards) {
            if (c.type == CardType.Ace) {
                c.value = 1;
            }
        }
    }

    /**
     * Updating score if given card caused threshold exceeding.
     *
     * @param card Given card
     */
    public void updateScore(Card card) {
        if (!card.isOpen) {
            return;
        }
        if (card.type == CardType.Ace && this.score > Game.threshold - CardType.Ace.getValue()) {
            downgradeAces();
            this.score = this.cards.stream().mapToInt(x -> x.value).sum();
        } else {
            this.score += card.value;
        }
    }

    public void peekCard(Card card) {
        this.cards.add(card);
        updateScore(card);
    }

    public String getTurnPrologueString() {
        return IO.playerTurnMsg;
    }

    public String getTurnEpilogueString(Card c) {
        return "Вы открыли карту " + c.toString();
    }

    public void takeTurn(Deck deck, Game gameContext) throws InvalidTurnException {
        IO.printTurnMsg(getTurnPrologueString());
        int input = IO.readPlayerInput();
        while (input == IO.keyPeekCard) {
            try {
                Card c = deck.peekCard(true);
                peekCard(c);
                IO.printTurnMsg(getTurnEpilogueString(c));
                IO.printHeldCards(gameContext);

                IO.printTurnMsg(getTurnPrologueString());
                input = IO.readPlayerInput();
            } catch (DeckIsEmptyException e) {
                throw new InvalidTurnException(e.getMessage());
            }
        }
    }

    /**
     * Resets player's state.
     */
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

    public String getCardsMsg() {
        return "\tВаши карты: " + cardsToString() + " => " + getScore();
    }
}
