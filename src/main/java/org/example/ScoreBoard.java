package org.example;

import java.util.List;

public interface ScoreBoard {

    void startGame(String homeTeam, String awayTeam);

    void finishGame(String homeTeam, String awayTeam);

    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

    List<Game> getSummary();
}
