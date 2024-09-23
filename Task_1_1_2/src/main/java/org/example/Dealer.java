package org.example;

import java.util.concurrent.TimeUnit;

public class Dealer extends Player {

    private boolean hasOpened = false;

    @Override
    public void reset() {
        super.reset();
        hasOpened = false;
    }

    @Override
    public String getTurnPrologueString() {
        return IO.dealerTurnMsg;
    }

    public String getTurnEpilogueString(Card c, boolean wasClosed) {
        return "Дилер открывает " + ((wasClosed) ? "закрытую " : "") + "карту " + c.toString();
    }

    @Override
    public void takeTurn(Deck deck, Game gameContext) throws InvalidTurnException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IO.printTurnMsg(getTurnPrologueString());
        if (!hasOpened) {
            this.cards.get(1).isOpen = true;
            hasOpened = true;
            updateScore(this.cards.get(1));
            IO.printTurnMsg(getTurnEpilogueString(this.cards.get(1), true));
            IO.printHeldCards(gameContext);
        }
        while (getScore() < 17) {
            try {
                peekCard(deck.peekCard(true));
            } catch (DeckIsEmptyException e) {
                throw new InvalidTurnException(e.toString());
            }
        }
    }


    @Override
    public String getCardsMsg() {
        return "\tКарты дилера: " + cardsToString() + (this.hasOpened ? " => " + getScore() : "");
    }

    public void dealCards(Deck deck, Player player) throws InvalidTurnException {
        try  {
            player.peekCard(deck.peekCard(true));
            player.peekCard(deck.peekCard(true));

            this.peekCard(deck.peekCard(true));
            this.peekCard(deck.peekCard(false));
        } catch (DeckIsEmptyException e) {
            throw new InvalidTurnException(e.getMessage());
        }

        IO.printCards(player);
        IO.printCards(this);
    }
}
