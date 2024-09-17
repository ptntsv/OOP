package org.example;

import java.util.concurrent.TimeUnit;

/**
 * Class that contains current state of game, i.e. main class
 */
public class Game {

    final private Deck deck = new Deck();
    final private Player player = new Player();
    final private Dealer dealer = new Dealer();
    final public static int threshold = 21;
    private int currentRound = 1;
    public WinState winState = WinState.NEITHER;
    /**
     * scoreTable[0] - player's score scoreTable[1] - dealer's score
     */
    int[] scoreTable = {0, 0};

    public Player getPlayer() {
        return this.player;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    /**
     * Checks for win condition every time when dealer/player took their turn and changes game
     * states (winner, score table, etc.).
     *
     * @param isRoundEnds If true - then we can compare player and dealer score and chose a winner.
     *                    If else - this method was called after dealer deal the cards.
     */
    public void checkWinCondition(boolean isRoundEnds) {
        int threshold = 21;
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
                scoreTable[0]++;
                break;
            }
            case DEALER_WON: {
                IO.printDealerWon();
                scoreTable[1]++;
                break;
            }
            case DRAW: {
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
            dealer.dealCards(deck, player);
            if (winState != WinState.NEITHER) {
                continue;
            }

            player.takeTurn(deck, this);
            if (winState != WinState.NEITHER) {
                continue;
            }
            dealer.takeTurn(deck, this);
            if (winState != WinState.NEITHER) {
                continue;
            }

            checkWinCondition(true);
        }
    }

    public void start() {
        IO.printWelcome();
        gameLoop();
    }

    public Game() {
    }
}
