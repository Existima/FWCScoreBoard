package org.example.model;

import java.util.Objects;

/**
 * Represents a game between two teams, including their names, scores,
 * and a unique identifier for the game.
 * Instances of this class are immutable.
 */
public class Game {
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;
    private final long id;

    public Game(String homeTeam, String awayTeam, long id) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.id = id;
    }

    public Game(Game previous, int homeScore, int awayScore) {
        this.homeTeam = previous.homeTeam;
        this.awayTeam = previous.awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.id = previous.getId();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return homeScore == game.homeScore && awayScore == game.awayScore && id == game.id && Objects.equals(homeTeam, game.homeTeam) && Objects.equals(awayTeam, game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, id);
    }
}
