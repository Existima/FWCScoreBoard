package org.example.service;

import org.example.model.Game;

import java.util.Comparator;

public class DefaultComparator implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        return Comparator
                .comparingInt((Game game) -> game.getAwayScore() + game.getHomeScore())
                .thenComparing(Game::getId).reversed().compare(o1, o2);
    }
}
