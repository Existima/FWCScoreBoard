package org.example.internal;

import org.example.model.Game;
import org.example.api.ScoreBoard;
import org.example.exceptions.InvalidGameNameException;
import org.example.exceptions.InvalidScoreException;
import org.example.exceptions.StartFinishGameException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoardImpl implements ScoreBoard {


    private final Map<String, Game> gamesBoard = new HashMap<>();

    /**
     * Starts a new game by adding it to the scoreboard with the given teams.
     * If the game is already started, an exception will be thrown.
     *
     * @param homeTeam the name of the home team. Must not be null or empty.
     * @param awayTeam the name of the away team. Must not be null or empty.
     * @throws InvalidGameNameException if the name of any team is null or blank.
     * @throws StartFinishGameException if the game is already started with the same teams.
     */
    @Override
    public void startGame(String homeTeam, String awayTeam) {
        validateNames(homeTeam, awayTeam);
        String key = generateGameKey(homeTeam, awayTeam);
        if (gamesBoard.containsKey(key)) {
            throw new StartFinishGameException("Game already started");
        } else {
            gamesBoard.put(key, new Game(homeTeam, awayTeam));
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
     * The game is identified using the names of the home and away teams.
     * Throws an exception if the scores are invalid or if the game is not found.
     *
     * @param homeTeam the name of the home team. Must not be null or empty.
     * @param awayTeam the name of the away team. Must not be null or empty.
     * @param homeScore the new score of the home team. Must be a non-negative integer.
     * @param awayScore the new score of the away team. Must be a non-negative integer.
     * @throws InvalidScoreException if either score is negative.
     * @throws InvalidGameNameException if the game with the specified teams does not exist on the scoreboard.
     */
    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new InvalidScoreException("Provided scores must be positive integers");
        }
        validateNames(homeTeam, awayTeam);
        String key = generateGameKey(homeTeam, awayTeam);
        if (gamesBoard.containsKey(key)) {
            Game updatedGame = new Game(homeTeam, awayTeam, homeScore, awayScore);
            gamesBoard.put(key, updatedGame);
        } else {
            throw new InvalidGameNameException("No such game in the list");
        }
    }

    /**
     * Retrieves a summary of all active games on the scoreboard.
     * The games are sorted by their combined score in descending order.
     * If two games have the same combined score, they are further sorted
     * by their start time in descending order.
     *
     * @return a list of active games, sorted by combined score and start time.
     */
    @Override
    public List<Game> getSummary() {
        return gamesBoard.values()
                .stream()
                .sorted(Comparator
                        .comparingInt((Game game) -> game.getAwayScore() + game.getHomeScore())
                        .thenComparing(Game::getStarted).reversed())
                .collect(Collectors.toList());
    }

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

//    public void showSummary(List<Game> list){
//        for (Game game : list){
//            System.out.println(game.getHomeTeam() + " " + game.getHomeScore() + " - " + game.getAwayTeam() + " " + game.getAwayScore());
//        }
//    }
}
