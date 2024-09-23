package org.example;

public enum Suit {
    Clubs("Трефы"),
    Diamonds("Бубны"),
    Hearts("Червы"),
    Spades("Пики");

    private final String name;

    Suit(String string) {
        this.name = string;
    }

    @Override
    public String toString() {
        return name;
    }
}
