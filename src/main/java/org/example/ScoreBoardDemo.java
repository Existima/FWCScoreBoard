package org.example;

import org.example.internal.ScoreBoardImpl;
import org.example.model.Game;

import java.util.List;

/**
 * The ScoreBoardDemo class demonstrates the usage of a scoreboard application
 * for managing sports games. It includes functionality for starting new games,
 * updating scores, finishing games, and displaying a summary of active games.
 *
 * This class is a demonstration utility and contains a main method that
 * provides a complete sequence of operations available in the scoreboard system.
 * The operations include:
 * - Starting new games between specified teams.
 * - Updating scores of active games.
 * - Fetching and displaying the summary of active games sorted by combined score.
 * - Finishing games and removing them from the scoreboard.
 *
 * Note:
 * - Games must be active to update their scores or display them in the summary.
 * - Teams must have unique names for proper identification.
 * - This is a final class and cannot be subclassed.
 */
public final class ScoreBoardDemo {

    public static void main(String[] args) {

        ScoreBoardImpl scoreBoard = new ScoreBoardImpl();
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<Game> games = scoreBoard.getSummary();
        scoreBoard.showSummary(games);

        scoreBoard.finishGame("Mexico", "Canada");
        games = scoreBoard.getSummary();
        scoreBoard.showSummary(games);
    }
}
