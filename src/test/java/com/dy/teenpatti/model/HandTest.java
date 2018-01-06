package com.dy.teenpatti.model;

import org.junit.Test;

public class HandTest {

    @Test
    public void isTrioTest() {

        Card c1 = new Card(11, Suit.HEARTS);
        Card c2 = new Card(13, Suit.SPADES);
        Card c3 = new Card(12, Suit.CLUBS);
        Card[] cards = {c1, c2, c3};
        Hand h = new Hand(cards);
        System.out.println(h);
    }
}
