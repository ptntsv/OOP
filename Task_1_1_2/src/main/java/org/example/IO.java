package org.example;

import java.util.Scanner;

/**
 * Class for BlackJack input-output.
 */
public class IO {

    public static final int keyStop = 0;
    public static final int keyPeekCard = 1;
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

    public static void printDraw() {
        System.out.print("Ничья.");
    }

    public static void printTurnMsg(String msg) {
        System.out.println(msg);
    }

    public static void printScore(ScoreTuple scoreTable) { System.out.print("Счёт " + scoreTable.playerScore + ":" + scoreTable.dealerScore); if (scoreTable.playerScore > scoreTable.dealerScore) { System.out.print(" в вашу пользу.");
        }
        if (scoreTable.playerScore < scoreTable.dealerScore) {
            System.out.print(" в пользу дилера.");
        }
        System.out.print("\n");
    }

    public static void printCards(Player player) {
        System.out.println(player.getCardsMsg());
    }

    public static int readPlayerInput() {
        int input = in.nextInt();
        while (input != keyStop && input != keyPeekCard) {
            printInputError();
            input = in.nextInt();
        }
        return input;
    }

    public static void printInputError() {
        System.out.println("Неверная команда, попробуйте ещё раз.");
    }
}
