package org.example.exceptions;

/**
 * Thrown to indicate that a game name or team name provided is invalid.
 *
 * This exception is used when a method requires the names of teams to be non-null
 * and non-empty strings. If a null or blank name is detected, this exception is thrown.
 *
 * Common scenarios where this exception is thrown include:
 * - Starting a new game with invalid team names.
 * - Finishing a game or updating its score where the team names are missing or incorrect.
 *
 * Users of the ScoreBoard API should ensure that all team names passed to its methods
 * are valid strings to prevent this exception.
 */
public class InvalidGameNameException extends RuntimeException{
    public InvalidGameNameException(String message){
        super(message);
    }
}
