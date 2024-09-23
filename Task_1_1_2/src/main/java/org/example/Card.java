package org.example;

public class Card {

    public Suit suit;
    public CardType type;
    public int value;
    public boolean isOpen = true;

    public Card(Suit suit, CardType type) {
        this.suit = suit;
        this.type = type;
        this.value = this.type.getValue();
    }

    @Override
    public String toString() {
        if (isOpen) {
            return this.type.toString() + " " + this.suit.toString() + " (" + this.value
                   + ")";
        }
        return "<закрытая карта>";
    }

    @Override
    public boolean equals(Object obj) {
        Card other = (Card) obj;
        return this.suit == other.suit && this.type == other.type && this.value == other.value;
    }
}
