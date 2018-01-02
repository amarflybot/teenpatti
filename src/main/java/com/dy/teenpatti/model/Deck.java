package com.dy.teenpatti.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int CARDS_IN_ONE_SUIT = 13;
    private static final int TOTAL_CARDS_IN_DECK = CARDS_IN_ONE_SUIT * Suit.values().length;

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit s : Suit.values()) {
            for (int i = 0; i < CARDS_IN_ONE_SUIT; i++) {
                this.cards.add(new Card(i + 1, s));
            }
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        cards.stream().forEach(c -> sb.append(c).append("\n"));
        return sb.toString();
    }
}
