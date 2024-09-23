package org.example;

public enum CardType {
    Two("Двойка", 2),
    Three("Тройка", 3),
    Four("Четверка", 4),
    Five("Пятерка", 5),
    Six("Шестерка", 6),
    Seven("Семерка", 7),
    Eight("Восьмерка", 8),
    Nine("Девятка", 9),
    Ten("Десятка", 10),
    Jack("Валет", 10),
    Queen("Дама", 10),
    King("Король", 10),
    Ace("Туз", 11);

    private final String name;
    private final int value;

    CardType(String string, int value) {
        this.name = string;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return name;
    }
}
