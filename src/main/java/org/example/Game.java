package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Game {
//todo make record + make class score (mutable)
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;
    private final LocalDateTime started;

    public Game(String homeTeam, String awayTeam) {
        this(homeTeam, awayTeam, 0, 0);
    }

    public Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.started = LocalDateTime.now();
    }

//    public void setHomeScore(int score) {
//        this.homeScore = score;
//    }
//
//    public void setAwayScore(int score) {
//        this.awayScore = score;
//    }

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

    public LocalDateTime getStarted() {
        return started;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return homeScore == game.homeScore && awayScore == game.awayScore && Objects.equals(homeTeam, game.homeTeam) && Objects.equals(awayTeam, game.awayTeam) && Objects.equals(started, game.started);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, started);
    }
}
