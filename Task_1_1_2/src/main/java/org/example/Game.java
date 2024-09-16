package org.example;

import java.util.concurrent.TimeUnit;

enum GameState {
    DRAW,
    PLAYER_WON,
    DEALER_WON,
    NEITHER
}

/**
 * Class that contains current state of game, i.e. main class
 */
public class Game {

    final private Deck deck = new Deck();
    final private Player player = new Player();
    final private Dealer dealer = new Dealer();
    final public static int threshold = 21;
    private int currentRound = 1;
    private GameState gameState;
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
     * @return Is there a winner?
     */
    private boolean checkWinCondition(boolean isRoundEnds) {
        boolean someOneWon = true;
        int threshold = 21;
        if (player.getScore() == threshold || dealer.getScore() > threshold) {
            gameState = GameState.PLAYER_WON;
        } else if (dealer.getScore() == threshold || player.getScore() > threshold) {
            gameState = GameState.DEALER_WON;
        } else if (isRoundEnds) {
            if (player.getScore() > dealer.getScore()) {
                gameState = GameState.PLAYER_WON;
            } else if (dealer.getScore() > player.getScore()) {
                gameState = GameState.DEALER_WON;
            } else {
                gameState = GameState.DRAW;
            }
        } else {
            someOneWon = false;
        }
        if (someOneWon) {
            switch (gameState) {
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
                }
            }
            IO.printScore(scoreTable);
        }
        return someOneWon;
    }

    /**
     * Resets game state (deck, player's and dealer's scores, etc.).
     */
    private void newRound() {
        IO.printRoundMsg(currentRound);
        currentRound++;
        deck.reset();
        player.reset();
        dealer.reset();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void gameLoop() {
        while (true) {
            newRound();
            dealer.dealCards(deck, player);
            if (checkWinCondition(false)) {
                continue;
            }

            player.takeTurn(deck, this);
            if (checkWinCondition(false)) {
                continue;
            }
            dealer.takeTurn(deck, this);
            if (checkWinCondition(false)) {
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
