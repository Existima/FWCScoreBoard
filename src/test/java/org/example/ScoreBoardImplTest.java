package org.example;

import org.example.exceptions.InvalidGameNameException;
import org.example.exceptions.InvalidScoreException;
import org.example.exceptions.StartFinishGameException;
import org.example.internal.ScoreBoardImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardImplTest {

    private ScoreBoardImpl scoreBoard;
    private String homeTeam;
    private String awayTeam;


    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoardImpl();
        homeTeam = "Mexico";
        awayTeam = "USA";
    }

    @Test
    void shouldStartNewGame() {
        //when
        scoreBoard.startGame(homeTeam, awayTeam);

        //then
        assertEquals(1, scoreBoard.getSummary().size());
        assertEquals("Mexico", scoreBoard.getSummary().getLast().getHomeTeam());
        assertEquals("USA", scoreBoard.getSummary().getLast().getAwayTeam());
        assertEquals(0, scoreBoard.getSummary().getLast().getHomeScore());
        assertEquals(0, scoreBoard.getSummary().getLast().getAwayScore());
        assertNotNull(scoreBoard.getSummary().getFirst());
    }

    @Test
    void shouldNotStartNewGameIfNamesIsEmptyOrNull() {
        //then
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.startGame("", null));
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.startGame(null, ""));
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.startGame("team1", ""));
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.startGame(null, "team2"));
        assertEquals(0, scoreBoard.getSummary().size());
    }

    @Test
    void shouldThrowExceptionWhenGameIsStarted() {
        //given
        //when
        scoreBoard.startGame(homeTeam, awayTeam);
        //then
        assertThrows(StartFinishGameException.class,
                () -> scoreBoard.startGame(homeTeam, awayTeam));
    }

    @Test
    void shouldFinishGameByNames() {
        //given
        scoreBoard.startGame(homeTeam, awayTeam);
        //when
        scoreBoard.finishGame(homeTeam, awayTeam);
        //then
        assertEquals(0, scoreBoard.getSummary().size());
    }

    @Test
    void shouldThrowExceptionWhenIsNoGameCanBeFinished() {
        //given
        scoreBoard.startGame(homeTeam, awayTeam);
        //then
        assertThrows(StartFinishGameException.class,
                () -> scoreBoard.finishGame("wrong name", awayTeam));
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    void shouldUpdateScore() {
        //given
        scoreBoard.startGame(homeTeam, awayTeam);
        //when
        scoreBoard.updateScore(homeTeam, awayTeam, 2, 1);
        //then
        assertEquals(2, scoreBoard.getSummary().getLast().getHomeScore());
        assertEquals(1, scoreBoard.getSummary().getLast().getAwayScore());
    }

    @Test
    void shouldNotUpdateScoreWithWrongName() {
        //given
        scoreBoard.startGame(homeTeam, awayTeam);
        //then
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.updateScore(homeTeam, "wrong name", 2, 1));
        assertThrows(InvalidGameNameException.class,
                () -> scoreBoard.updateScore(homeTeam, "wrong name", 2, 1));
        assertEquals(0, scoreBoard.getSummary().getLast().getHomeScore());
        assertEquals(0, scoreBoard.getSummary().getLast().getAwayScore());
    }

    @Test
    void shouldNotUpdateScoreWithInvalidIntegers() {
        //given
        scoreBoard.startGame(homeTeam, awayTeam);
        //then
        assertThrows(InvalidScoreException.class,
                () -> scoreBoard.updateScore(homeTeam, awayTeam, -2, 1));
        assertThrows(InvalidScoreException.class,
                () -> scoreBoard.updateScore(homeTeam, awayTeam, 2, -1));
        assertEquals(0, scoreBoard.getSummary().getLast().getHomeScore());
        assertEquals(0, scoreBoard.getSummary().getLast().getAwayScore());
    }

    @Test
    void shouldReturnOrderedListOfActiveGames(){
        //given
        scoreBoard.startGame("England", "Italy");
        scoreBoard.startGame("Germany", "France");
        scoreBoard.startGame("Poland", "Greece");
        scoreBoard.startGame("Ukraine", "Spain");
        scoreBoard.startGame("Norway", "Portugal");
        //when
        scoreBoard.updateScore("England", "Italy", 2, 2);
        scoreBoard.updateScore("Germany", "France", 1, 0);
        scoreBoard.updateScore("Poland", "Greece", 4, 2);
        scoreBoard.updateScore("Norway", "Portugal", 3, 1);
        var scoreList = scoreBoard.getSummary();
        //then
        assertEquals(5, scoreList.size());

        assertEquals("Poland", scoreList.getFirst().getHomeTeam()); //first in the list bcs have biggest total score
        assertEquals("Greece", scoreList.getFirst().getAwayTeam());

        assertEquals("Norway", scoreList.get(1).getHomeTeam()); //is second in the list with equal score bcs match started first
        assertEquals("Portugal", scoreList.get(1).getAwayTeam());

        assertEquals("Ukraine", scoreList.getLast().getHomeTeam()); //is last in the bcs lowest total score
        assertEquals("Spain", scoreList.getLast().getAwayTeam());
    }
}