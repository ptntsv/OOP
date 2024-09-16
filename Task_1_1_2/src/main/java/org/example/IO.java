package org.example;

import java.util.Scanner;

/**
 * Class for BlackJack input-output
 */
public class IO {

    public static Scanner in = new Scanner(System.in);
    public static final String playerTurnMsg =
        "Ваш ход\n-------\nВведите \"1\", чтобы взять карту, и \"0\", чтобы остановиться....";
    public static final String dealerTurnMsg =
        "Ход дилера\n-------";

    public static void printHeldCards(Game gameContext) {
        IO.printCards(gameContext.getPlayer());
        IO.printCards(gameContext.getDealer());
    }

    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    public static void printRoundMsg(int round) {
        System.out.println("Раунд " + round);
        System.out.println("Дилер раздал карты");
    }

    public static void printPlayerWon() {
        System.out.print("Вы выиграли раунд! ");
    }

    public static void printDealerWon() {
        System.out.print("Дилер выиграл раунд. ");
    }

    public static void printTurnMsg(String msg) {
        System.out.println(msg);
    }

    public static void printScore(int[] scoreTable) {
        System.out.print("Счёт " + scoreTable[0] + ":" + scoreTable[1]);
        if (scoreTable[0] > scoreTable[1]) {
            System.out.print(" в вашу пользу.");
        }
        if (scoreTable[0] < scoreTable[1]) {
            System.out.print(" в пользу дилера.");
        }
        System.out.print("\n");
    }

    public static void printCards(Player player) {
        System.out.println(player.printCardsMsg());
    }

    public static int readPlayerInput() {
        return in.nextInt();
    }
}
