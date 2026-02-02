package org.example.exceptions;

/**
 * Thrown to indicate an error related to starting or finishing a game on the scoreboard.
 *
 * This exception is used in scenarios where specific constraints or rules for starting
 * or finishing a game are violated. Common scenarios where this exception might be thrown include:
 *
 * - Attempting to start a game that is already active on the scoreboard with the same teams.
 * - Attempting to finish a game that does not exist on the scoreboard.
 *
 * Users of the scoreboard API should ensure that games are uniquely identified and
 * adhere to the required constraints during operations to avoid triggering this exception.
 */
public class StartFinishGameException extends RuntimeException {
    public StartFinishGameException(String message) {
        super(message);
    }
}
