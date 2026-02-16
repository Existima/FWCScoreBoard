package org.example;

import org.example.model.Game;
import org.example.service.DefaultComparator;
import org.example.service.DefaultDataSource;
import org.example.service.DefaultIdGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ServiceTest {
    @Test
    void testCompareGamesWithDifferentScores() {
        //given
        Game game1 = new Game("Team1", "Team2", 1);
        Game game2 = new Game(new Game("Team3", "Team4", 2), 3, 4);
        DefaultComparator comparator = new DefaultComparator();
        //when
        int result = comparator.compare(game1, game2);
        //then
        assertTrue(result > 0, "Game with higher total score should be ranked higher.");
    }

    @Test
    void testCompareGamesWithEqualScoresAndDifferentIds() {
        //given
        Game game1 = new Game(new Game("Team1", "Team2", 1), 3, 4);
        Game game2 = new Game(new Game("Team3", "Team4", 2), 3, 4);
        DefaultComparator comparator = new DefaultComparator();
        //when
        int result = comparator.compare(game1, game2);
        //then
        assertTrue(result > 0, "Game with the lower ID should be ranked higher when scores are the same.");
    }

    @Test
    void testGetWithExistingKey() {
        //given
        DefaultDataSource dataSource = new DefaultDataSource();
        String key = "game1";
        Game game = new Game("Team A", "Team B", 1);
        dataSource.put(key, game);
        //when
        Game result = dataSource.get(key);
        //then
        assertNotNull(result, "The result should not be null for an existing key.");
        assertEquals(game, result, "The result should match the expected game.");
    }

    @Test
    void testGetWithNonExistingKey() {
        //given
        DefaultDataSource dataSource = new DefaultDataSource();
        String key = "nonExistingKey";
        //when
        Game result = dataSource.get(key);
        //then
        assertNull(result, "The result should be null for a non-existing key.");
    }

    @Test
    void testGetAfterRemovingKey() {
        //given
        DefaultDataSource dataSource = new DefaultDataSource();
        String key = "game2";
        Game game = new Game("Team C", "Team D", 2);
        dataSource.put(key, game);
        dataSource.remove(key);
        //when
        Game result = dataSource.get(key);
        //then
        assertNull(result, "The result should be null after the key has been removed.");
    }

    @Test
    void testGetReturnsInitialId() {
        //given
        DefaultIdGenerator idGenerator = new DefaultIdGenerator();
        //when
        long result = idGenerator.get();
        //then
        assertEquals(1L, result, "The initial ID should be 1.");
    }

    @Test
    void testGetAfterInitialIncrement() {
        //given
        DefaultIdGenerator idGenerator = new DefaultIdGenerator();
        //when
        idGenerator.increment();
        long result = idGenerator.get();
        //then
        assertEquals(2L, result, "The ID after one increment should be 2.");
    }

    @Test
    void testGetAfterMultipleIncrements() {
        //given
        DefaultIdGenerator idGenerator = new DefaultIdGenerator();
        //when
        idGenerator.increment();
        idGenerator.increment();
        idGenerator.increment();
        long result = idGenerator.get();
        //then
        assertEquals(4L, result, "The ID after three increments should be 4.");
    }
}
