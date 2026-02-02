package org.example;

import org.example.api.ScoreBoard;
import org.example.internal.ScoreBoardImpl;

/**
 * Demonstrates the usage of the ScoreBoard interface and its implementation.
 *
 * This class serves as an entry point to showcase basic operations like starting a game,
 * updating the score, and retrieving a summary of active games.
 *
 * The operations performed include:
 * - Initializing a ScoreBoard implementation.
 * - Starting a new game between two specified teams.
 * - Updating the score of the ongoing game.
 * - Printing a summary of all active games to the console.
 *
 * The class uses the ScoreBoard interface to manage game states and interact with the
 * ScoreBoard implementation (ScoreBoardImpl).
 *
 * It expects the ScoreBoard implementation to handle operations like game validation,
 * score updates, and proper game tracking.
 *
 * This is a utility class and cannot be instantiated.
 */
public final class ScoreBoardDemo {

    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        scoreBoard.startGame("Mexico", "USA");
        scoreBoard.updateScore("Mexico", "USA", 2, 1);
        scoreBoard.getSummary().forEach(System.out::println);
    }
}
