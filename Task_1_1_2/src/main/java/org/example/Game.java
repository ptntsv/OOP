package org.example;

import java.util.concurrent.TimeUnit;

/**
 * Class that contains current state of game, i.e. main class
 */
public class Game {

    private final Deck deck = new Deck();
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();
    public static final int threshold = 21;
    private int currentRound = 1;

    public int getCurrentRound() {
        return currentRound;
    }

    public WinState winState = WinState.NEITHER;
    public ScoreTuple scoreTable = new ScoreTuple();

    public Player getPlayer() {
        return this.player;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Checks for win condition every time when dealer/player took their turn and changes game
     * states (winner, score table, etc.).
     *
     * @param isRoundEnds If true - then we can compare player and dealer score and chose a winner.
     *                    If else - this method was called after dealer deal the cards.
     */
    public void checkWinCondition(boolean isRoundEnds) {
        if (player.getScore() == threshold || dealer.getScore() > threshold) {
            winState = WinState.PLAYER_WON;
        } else if (dealer.getScore() == threshold || player.getScore() > threshold) {
            winState = WinState.DEALER_WON;
        } else if (isRoundEnds) {
            if (player.getScore() > dealer.getScore()) {
                winState = WinState.PLAYER_WON;
            } else if (dealer.getScore() > player.getScore()) {
                winState = WinState.DEALER_WON;
            } else {
                winState = WinState.DRAW;
            }
        }
        switch (winState) {
            case PLAYER_WON: {
                IO.printPlayerWon();
                scoreTable.playerScore++;
                break;
            }
            case DEALER_WON: {
                IO.printDealerWon();
                scoreTable.dealerScore++;
                break;
            }
            case DRAW: {
                IO.printDraw();
                break;
            }
            default: {
                return;
            }
        }
        IO.printScore(scoreTable);
    }

    /**
     * Resets game state (deck, player's and dealer's scores, etc.).
     */
    private void newRound() {
        IO.printRoundMsg(currentRound);
        currentRound++;
        winState = WinState.NEITHER;
        deck.reset();
        player.reset();
        dealer.reset();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Main game loop.
     */
    private void gameLoop() {
        while (true) {
            newRound();
            try {
                dealer.dealCards(deck, player);
                checkWinCondition(false);
                if (winState != WinState.NEITHER) {
                    continue;
                }

                player.takeTurn(deck, this);
                checkWinCondition(false);
                if (winState != WinState.NEITHER) {
                    continue;
                }

                dealer.takeTurn(deck, this);
                checkWinCondition(true);

            } catch (InvalidTurnException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void start() {
        IO.printWelcome();
        gameLoop();
    }

    public Game() {
    }
}
