package org.example;

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

    //todo java docs
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
