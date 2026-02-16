package org.example.service;

import org.example.model.Game;

import java.util.Comparator;

/**
 * A comparator implementation for comparing instances of the {@code Game} class.
 * <p>
 * This comparator orders games based on the sum of their home and away scores in descending order.
 * If two games have the same score sum, they are further compared based on their unique identifiers
 * in descending order.
 * <p>
 * This implementation is intended to define the default sorting behavior for the {@code Game} class.
 */
public class DefaultComparator implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        return Comparator
                .comparingInt((Game game) -> game.getAwayScore() + game.getHomeScore())
                .thenComparing(Game::getId).reversed().compare(o1, o2);
    }
}
