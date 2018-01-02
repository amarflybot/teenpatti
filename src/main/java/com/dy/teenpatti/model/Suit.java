package com.dy.teenpatti.model;

public enum Suit {
    DIAMONDS('\u2666'),CLUBS('\u2663'),HEARTS('\u2764'),SPADES('\u2660');

    private char unicodeChar;

    Suit(char unicodeChar) {
        this.unicodeChar = unicodeChar;
    }

    @Override
    public String toString() {
        return String.valueOf(unicodeChar);
    }
}
