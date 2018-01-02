package com.dy.teenpatti.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Card implements Comparable<Card> {


    private static final int ACE_VAL = 100;
    private int val;
    private Suit suit;

    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public static Card returnHigherCard(Card c1, Card c2) {
        return Collections.max(Arrays.asList(new Card[]{c1, c2}));
    }

    @Override
    public String toString() {
        return getStringWithoutSuit() + suit;
    }

    public String getStringWithoutSuit() {
        String valString = null;

        switch (val) {
            case 13:
                valString = "K";
                break;
            case 12:
                valString = "Q";
                break;
            case 11:
                valString = "J";
                break;
            case 1:
                valString = "A";
                break;
            default:
                valString = String.valueOf(val);
                break;
        }

        return valString;
    }

    private int getValForComparison() {
        if (val == 1) {
            return ACE_VAL;
        } else return val;
    }

    @Override
    public int compareTo(Card o) {
        int resValCompare = Integer.compare(this.getValForComparison(), o.getValForComparison());
        if (resValCompare == 0) {
            return this.suit.compareTo(o.suit);
        } else {
            return resValCompare;
        }
    }

    public boolean isSameSuit(Card c) {
        return this.suit == c.suit;
    }

    public boolean isSameValue(Card c) {
        return this.val == c.val;
    }

    public boolean isAdjacentTo(Card c) {
        return Math.abs(this.val - c.val) == 1;
    }

}
