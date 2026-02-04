# FWCScoreBoard
version = 1.0.4

Football World Cup Scoreboard is a library that displays matches and scores.

The key class in this library is Game, an immutable object that provides all the necessary information about a game on the board.

The ScoreBoard interface exposes four methods: start a new game, finish a game, update scores, and get a summary of current games.
A Game object uses an in-memory store (a HashMap), which means each new instance of this class represents a separate scoreboard. To correctly reflect games after a score update, the library leverages HashMap semantics: after creating a new, updated object using a secondary constructor and the fields of the previous game, the game that needs to be updated is replaced in memory under the same key. This ensures encapsulation while providing the required functionality.

To satisfy the second requirement—retrieving games in a specific order—the Game class contains an id field. This field is auto-incremented whenever a new game is started on the scoreboard. After sorting current games by total score, games with the same total are ordered by recency, with the most recently added first.
Edge-case tests show that using Date/Time or Instant instead of id is not reliable. When two or more games are started at the same time, their timestamp values can be equal, which can lead to race conditions: the summary may not consistently return the same order.

Additional method showSummary() inside ScoreBoardDemo simply created for demonstration scoreboard main operations. His structure is representing all information as the last requirement ask.