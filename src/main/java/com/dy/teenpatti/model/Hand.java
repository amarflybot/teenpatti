package com.dy.teenpatti.model;

import java.util.Arrays;
import java.util.Collections;

public class Hand implements Comparable<Hand> {

    private final Card[] cards;
    private final HandClass handClass;
    private final Card mostSignificantCard;

    public Hand(Card[] cards) {
        this.cards = cards;
        handClass = deriveHandClass();
        if (handClass == HandClass.PAIR) {
            mostSignificantCard = getPairCard();
        } else {
            // Default is highCard
            mostSignificantCard = getHighCard();
        }
    }


    private HandClass deriveHandClass() {
        if (isTrio()) {
            return HandClass.TRIO;
        }
        // Is pakka sequence
        if (isPakkaSequence()) {
            return HandClass.PAKKA_SEQUENCE;
        }
        // Is sequence
        if (isSeq()) {
            return HandClass.SEQUENCE;
        }
        // Is Color
        if (isColor()) {
            return HandClass.COLOR;
        }
        // Is Pair
        if (isPair()) {
            return HandClass.PAIR;
        }
        return HandClass.HIGH_CARD;
    }

    private Card getPairCard() {
        Card[] cardList = this.getCards();
        Card pairCard = getHighCard();
        if (cardList[0].isSameValue(cardList[1])) {
            pairCard = Card.returnHigherCard(cardList[0], cardList[1]);
        }
        if (cardList[0].isSameValue(cardList[2])) {
            pairCard = Card.returnHigherCard(cardList[0], cardList[2]);
        }
        if (cardList[1].isSameValue(cardList[2])) {
            pairCard = Card.returnHigherCard(cardList[1], cardList[2]);
        }
        return pairCard;
    }

    public HandClass getHandClass() {
        return handClass;
    }

    public String getHandName() {
        return handClass + "," + this.getMostSignificantCard();
    }

    public Card getMostSignificantCard() {
        return mostSignificantCard;
    }

    private boolean isPair() {
        return (cards[0].isSameValue(cards[1]) || cards[0].isSameValue(cards[2]) || cards[1].isSameValue(cards[2]));
    }

    private boolean isPakkaSequence() {
        return isColor() && isSeq();
    }

    private boolean isSeq() {
        // no two cards equal
        if (isTrio()) return false;

        int adjCount = 0;
        // two pairs need to be adjacent
        if (cards[0].isAdjacentTo(cards[1])) {
            adjCount++;
        }
        if (cards[0].isAdjacentTo(cards[2])) {
            adjCount++;
        }
        if (cards[1].isAdjacentTo(cards[2])) {
            adjCount++;
        }

        return adjCount == 2;
    }

    private boolean isColor() {
        return cards[0].isSameSuit(cards[1]) && cards[0].isSameSuit(cards[2]);
    }

    private boolean isTrio() {
        return cards[0].isSameValue(cards[1]) && cards[0].isSameValue(cards[2]);
    }

    private Card getHighCard() {
        return Collections.max(Arrays.asList(cards));
    }

    @Override
    public int compareTo(Hand o) {
        int handClassComparisonValue = this.getHandClass().compareTo(o.getHandClass());
        if (handClassComparisonValue == 0) {
            return this.getMostSignificantCard().compareTo(o.getMostSignificantCard());
        } else {
            return handClassComparisonValue;
        }
    }

    @Override
    public String toString() {
        return handClass + " with " + getMostSignificantCard() + ":\t" +
                cards[0] + "," +
                cards[1] + "," +
                cards[2];
    }

    public Card[] getCards() {
        return cards;
    }
}
