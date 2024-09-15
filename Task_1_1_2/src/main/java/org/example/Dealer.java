package org.example;

import java.util.concurrent.TimeUnit;

public class Dealer extends Player {

    private boolean hasOpened = false;

    @Override
    public void win(int[] scoreTable) {
        System.out.println("Дилер выигрывает раунд! Счёт " + scoreTable[0] + ":" + ++scoreTable[1]);
    }

    @Override
    public void reset() {
        super.reset();
        hasOpened = false;
    }

    @Override
    public void printTurnPrologue() {
        System.out.println(IO.dealerTurnMsg);
    }

    public void printTurnEpilogue(Card c, IO io, boolean wasClosed) {
        final String msg =
            "Дилер открывает " + ((wasClosed) ? "закрытую " : "") + "карту " + c.toString();
        System.out.println(msg);
        io.printHeldCards();
    }

    @Override
    public void takeTurn(Deck deck, IO io) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printTurnPrologue();
        if (!hasOpened) {
            this.cards.get(1).isOpen = true;
            hasOpened = true;
            printTurnEpilogue(this.cards.get(1), io, true);
        }
        while (getScore() < 17) {
            peekCard(deck.peekCard(true));
        }
    }


    @Override
    public void printCards() {
        System.out.println(
            "\tКарты дилера: " + cardsToString() + (this.hasOpened ? " => " + getScore() : ""));
    }

    public void dealCards(Deck deck, Player player) {
        player.peekCard(deck.peekCard(true));
        player.peekCard(deck.peekCard(true));

        this.peekCard(deck.peekCard(true));
        this.peekCard(deck.peekCard(false));

        player.printCards();
        this.printCards();
    }
}
