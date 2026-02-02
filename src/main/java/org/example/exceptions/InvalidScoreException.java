package org.example.exceptions;

/**
 * Thrown to indicate that an invalid score has been provided for an operation
 * related to a game on the scoreboard. This exception is typically used to enforce
 * that scores must be non-negative integers during score updates.
 *
 * Scenarios where this exception might be thrown include:
 * - Attempting to update the score with negative integers for either team.
 *
 * Users of the scoreboard API should ensure that provided scores are valid
 * to avoid triggering this exception.
 */
public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String message) {
        super(message);
    }
}
