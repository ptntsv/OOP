package org.example;

public enum Suit {
    Clubs("Трефы"),
    Diamonds("Бубны"),
    Hearts("Червы"),
    Spades("Пики");

    private final String toStr;

    Suit(String string) {
        this.toStr = string;
    }

    @Override
    public String toString() {
        return toStr;
    }
}
