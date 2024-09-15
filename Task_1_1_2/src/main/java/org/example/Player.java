package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    final ArrayList<Card> cards = new ArrayList<>();

    private int score;

    public int getScore() {
        return score;
    }

    private void updateScore(Card card) {
        if (card.type == CardType.Ace && this.score > 21) {
            this.score += 1;
        } else {
            this.score += card.value;
        }
    }

    public void peekCard(Card card) {
        this.cards.add(card);
        updateScore(card);
    }

    public void win(int[] scoreTable) {
        System.out.println("Вы выигрываете раунд! Счёт " + ++scoreTable[0] + ":" + scoreTable[1]);
    }

    public void printTurnPrologue() {
        System.out.println(IO.playerTurnMsg);
    }

    public void printTurnEpilogue(Card c, IO io) {
        final String msg = "Вы открыли карту " + c.toString();
        System.out.println(msg);
        io.printHeldCards();
    }

    public void takeTurn(Deck deck, IO io) {
        printTurnPrologue();
        Scanner reader = new Scanner(System.in);
        int input = reader.nextInt();
        while (input == 1) {
            Card c = deck.peekCard(true);
            peekCard(c);
            printTurnEpilogue(c, io);

            printTurnPrologue();
            input = reader.nextInt();
        }
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

    public void printCards() {
        System.out.println("\tВаши карты: " + cardsToString() + " => " + getScore());
    }

}
