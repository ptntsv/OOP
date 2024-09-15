package org.example;

import java.util.concurrent.TimeUnit;

public class Game {

    final private Deck deck = new Deck();
    final private Player player = new Player();
    final private Dealer dealer = new Dealer();
    final private IO io = new IO(this);
    private int currentRound = 1;
    int[] scoreTable = {0, 0};

    public Player getPlayer() {
        return this.player;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    private boolean checkWinCondition(boolean isRoundEnds) {
        boolean ret = true;
        int threshold = 21;
        if (player.getScore() == threshold || dealer.getScore() > threshold) {
            player.win(scoreTable);
        } else if (dealer.getScore() == threshold || player.getScore() > threshold) {
            dealer.win(scoreTable);
        } else if (isRoundEnds) {
            if (player.getScore() >= dealer.getScore()) {
                player.win(scoreTable);
            } else {
                dealer.win(scoreTable);
            }
        } else {
            ret = false;
        }
        return ret;
    }

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

            player.takeTurn(deck, io);
            if (checkWinCondition(false)) {
                continue;
            }
            dealer.takeTurn(deck, io);
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
