package org.example;

import java.util.Scanner;

public class IO {

    private final Game gameContext;
    public static Scanner in = new Scanner(System.in);
    public static final String playerTurnMsg =
        """
            Ваш ход
            -------
            Введите "1", чтобы взять карту, и "0", чтобы остановиться...
            """;
    public static final String dealerTurnMsg =
        """
            Ход дилера
            -------
            """;

    public void printHeldCards() {
        this.gameContext.getPlayer().printCards();
        this.gameContext.getDealer().printCards();
    }

    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    public static void printRoundMsg(int round) {
        System.out.println("Раунд " + round);
        System.out.println("Дилер раздал карты");
    }
    public IO(Game game) {
        this.gameContext = game;
    }
}
