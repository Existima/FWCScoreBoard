package org.example;

import org.example.exceptions.InvalidGameNameException;
import org.example.exceptions.InvalidScoreException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoardImpl implements ScoreBoard {


    private final Map<String, Game> gamesBoard = new HashMap<>(); //todo use hash set?
//todo java docs
    @Override
    public void startGame(String homeTeam, String awayTeam) {
        if(homeTeam == null || homeTeam.isBlank() || awayTeam == null || awayTeam.isBlank()){
            throw  new InvalidGameNameException("Name(s) must be not null or empty");
        }
        String key = homeTeam + "-" + awayTeam; //todo getkey private method || make key as field from Game
        if (gamesBoard.containsKey(key)) {
            System.out.println("Game already started");
        } else {
            gamesBoard.put(key, new Game(homeTeam, awayTeam));
        }
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        String key = homeTeam + "-" + awayTeam;
        if (!gamesBoard.containsKey(key)) {
            System.out.println("There is no such game in the list");
        } else {
            gamesBoard.remove(key);
        }
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if(homeScore < 0 || awayScore < 0){
            throw new InvalidScoreException("Provided scores must be positive integers");
        }
        String key = homeTeam + "-" + awayTeam;
        if (gamesBoard.containsKey(key)) {
            Game updatedGame = gamesBoard.get(key);
            updatedGame.setHomeScore(homeScore);
            updatedGame.setAwayScore(awayScore);
            gamesBoard.put(key, updatedGame);
        } else {
            throw new InvalidGameNameException("No such game in the list");//todo
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

//    public void showSummary(List<Game> list){
//        for (Game game : list){
//            System.out.println(game.getHomeTeam() + " " + game.getHomeScore() + " - " + game.getAwayTeam() + " " + game.getAwayScore());
//        }
//    }
}
