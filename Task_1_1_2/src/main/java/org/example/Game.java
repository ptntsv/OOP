package org.example;

public class Game {

    private Deck deck = new Deck();
    private Player you = new Player();
    private Player dealer = new Player();
    private int currentRound = 1;
    private final int winCondition = 21;
    public boolean isOver = false;

    private void peekCard(Player player, boolean isOpen) {
        player.addCard(deck.peekCard());
        if (deck.isEmpty()) {
            isOver = true;
        }
    }

    private boolean isWinner(Player player) {
        return player.getScore() == winCondition;
    }

    private void dealTheCards() {
        peekCard(you, true);
        peekCard(you, true);
        System.out.println("\t Ваши карты: " + you.cardsToString());

        peekCard(dealer, true);
        peekCard(dealer, false);
        System.out.println("\t Карты дилера: " + dealer.cardsToString());
    }

    private void dealerTurn() {
    }

    private void playerTurn() {

    }

    private void newRound() {
        currentRound++;
        deck.reset();
        you.reset();
        dealer.reset();
    }

    private void gameLoop() {
        while (!isOver) {
            IO.printRoundMsg(currentRound);
            dealTheCards();

            if (isWinner(you)) {
                isOver = true;
                return;
            } else if (isWinner(dealer)) {
                isOver = true;
                return;
            }
            playerTurn();
            dealerTurn();

            newRound();
        }
    }

    public void start() {
        IO.printWelcome();
        gameLoop();
    }

    public Game() {
    }
}
