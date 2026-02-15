package org.example.api;

import org.example.model.Game;

import java.util.List;

/**
 * Interface for managing a sports scoreboard that tracks active games and their scores.
 * It provides methods to start and finish games, update scores, and get a summary
 * of all active games.
 */
public interface ScoreBoard {

    void startGame(String homeTeam, String awayTeam);

    void finishGame(String homeTeam, String awayTeam);

    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

    List<Game> getSummary();
}
//todo replace parameters to Object