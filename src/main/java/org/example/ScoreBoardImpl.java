package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreBoard {


    public Map<String, Integer> startGame (HashMap<String, Integer> scoreBoard, String homeTeam, String awayTeam){
        String matchKey = homeTeam + " - " + awayTeam;
        if(scoreBoard.containsKey(matchKey)){
            System.out.println("Match already started");
            return scoreBoard;
        } else {
            scoreBoard.put(matchKey, 0);
        }



    }
}
