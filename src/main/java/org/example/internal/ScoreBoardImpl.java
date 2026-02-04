package org.example.internal;

import org.example.api.ScoreBoard;
import org.example.exceptions.InvalidGameNameException;
import org.example.exceptions.InvalidScoreException;
import org.example.exceptions.StartFinishGameException;
import org.example.model.Game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoardImpl implements ScoreBoard {

    private final Map<String, Game> gamesBoard = new HashMap<>();
    private static long id = 1;

    /**
     * Initiates a new game on the scoreboard by adding it with the provided home and away teams.
     * The method validates the team names to ensure they are not null or empty.
     * If a game already exists on the scoreboard with the same teams, an exception is thrown.
     *
     * @param homeTeam the name of the home team. Must not be null or empty.
     * @param awayTeam the name of the away team. Must not be null or empty.
     * @throws InvalidGameNameException if either team name is null or empty.
     * @throws StartFinishGameException if the game with the provided teams is already active.
     */
    @Override
    public void startGame(String homeTeam, String awayTeam) {
        validateNames(homeTeam, awayTeam);
        String key = generateGameKey(homeTeam, awayTeam);
        if (gamesBoard.containsKey(key)) {
            throw new StartFinishGameException("Game already started");
        } else {
            gamesBoard.put(key, new Game(homeTeam, awayTeam, id));
            id++;
        }
    }

    /**
     * Removes a game from the scoreboard, effectively marking the game as finished.
     * <p>
     * The game is identified by the names of the home and away teams. If the game
     * is not active on the scoreboard, an exception is thrown. Before removing
     * the game, the team names are validated to ensure they are not null or empty.
     *
     * @param homeTeam the name of the home team. Must not be null or empty.
     * @param awayTeam the name of the away team. Must not be null or empty.
     * @throws InvalidGameNameException if either team name is null or blank.
     * @throws StartFinishGameException if there is no such game in the scoreboard.
     */
    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        validateNames(homeTeam, awayTeam);
        String key = generateGameKey(homeTeam, awayTeam);
        if (!gamesBoard.containsKey(key)) {
            throw new StartFinishGameException("There is no such game in the list");
        } else {
            gamesBoard.remove(key);
        }
    }

    /**
     * Updates the score of an active game on the scoreboard.
     * The method validates the provided scores to ensure they are non-negative integers
     * and validates the team names to confirm they are not null or blank.
     * If the game is not found on the scoreboard, an exception is thrown.
     *
     * @param homeTeam the name of the home team. Must not be null or blank.
     * @param awayTeam the name of the away team. Must not be null or blank.
     * @param homeScore the new score of the home team. Must be a non-negative integer.
     * @param awayScore the new score of the away team. Must be a non-negative integer.
     * @throws InvalidScoreException if any of the provided scores are negative.
     * @throws InvalidGameNameException if either of the team names are null or blank,
     *                                   or if the game does not exist on the scoreboard.
     */
    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new InvalidScoreException("Provided scores must be positive integers");
        }
        validateNames(homeTeam, awayTeam);
        String key = generateGameKey(homeTeam, awayTeam);
        if (gamesBoard.containsKey(key)) {
            Game updatedGame = new Game(gamesBoard.get(key), homeScore, awayScore);
            gamesBoard.put(key, updatedGame);
        } else {
            throw new InvalidGameNameException("No such game in the list");
        }
    }

    /**
     * Retrieves a summary of all active games on the scoreboard, sorted by total score in descending order.
     * If two games have the same total score, they are further sorted by their unique IDs in descending order.
     *
     * @return a list of {@code Game} objects representing the sorted summary of active games.
     */
    @Override
    public List<Game> getSummary() {
        return gamesBoard.values()
                .stream()
                .sorted(Comparator
                        .comparingInt((Game game) -> game.getAwayScore() + game.getHomeScore())
                        .thenComparing(Game::getId).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Generates a unique key for a game based on the names of the home and away teams.
     * The key is created by concatenating the team names with a hyphen.
     *
     * @param homeTeam the name of the home team. Must not be null or empty.
     * @param awayTeam the name of the away team. Must not be null or empty.
     * @return a string representing the unique key for the game.
     */
    private String generateGameKey(String homeTeam, String awayTeam) {
        return homeTeam + "-" + awayTeam;
    }

    /**
     * Validates the names of the home and away teams to ensure they are not null or blank.
     * If any of the names are invalid, an {@link InvalidGameNameException} is thrown.
     *
     * @param homeTeam the name of the home team to validate. Must not be null or blank.
     * @param awayTeam the name of the away team to validate. Must not be null or blank.
     * @throws InvalidGameNameException if either team name is null or empty.
     */
    private void validateNames(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isBlank() || awayTeam == null || awayTeam.isBlank()) {
            throw new InvalidGameNameException("Name(s) must be not null or empty");
        }
    }

}
