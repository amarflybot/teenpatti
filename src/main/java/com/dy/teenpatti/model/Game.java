package com.dy.teenpatti.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    public static void main(String[] args) {
        playFewGames(5,4);
    }

    List<Hand> hands = new ArrayList<>();
    Deck deck;

    public Game(int numberOfPlayers) {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        for (int i = 0; i < numberOfPlayers; i++) {
            hands.add(new Hand(new Card[]{cards.get(i * 3), cards.get(i * 3 + 1), cards.get(i * 3 + 2)}));
        }
    }

    public Hand getWinner() {
        return Collections.max(hands);
    }

    public static void playFewGames(int numberOfPlayers, int numOfGames) {
        for (int i = 0; i < numOfGames; i++) {
            Game g = new Game(numberOfPlayers);
            g.hands.stream().forEach(System.out::println);
            System.out.println("Winner is: " + g.getWinner());
            System.out.println("\n*******************************\n");
        }
    }

    public static void runSimulation(int numberOfPlayers, int numOfGames) {
        List<Hand> winners = new ArrayList<>();
        for (int i = 0; i < numOfGames; i++) {
            winners.add(new Game(numberOfPlayers).getWinner());
        }

        Map<HandClass, List<Hand>> statsByHandClass = winners.stream().collect(Collectors.groupingBy(t -> t.getHandClass()));

        for (Map.Entry<HandClass, List<Hand>> m : statsByHandClass.entrySet()) {
            List<Hand> sortedHands = m.getValue();
            Collections.sort(sortedHands);
            Collections.reverse(sortedHands);
            Map<String, Long> statsForHandClass = sortedHands.stream().collect(Collectors.groupingBy(t -> t.getMostSignificantCard().getStringWithoutSuit(), Collectors.counting()));
            for (Map.Entry<String, Long> ent : statsForHandClass.entrySet()) {
                System.out.println(m.getKey() + "," + ent.getKey() + "," + ent.getValue() + "," + (float) ent.getValue() * 100 / numOfGames);
            }
        }
    }

}
