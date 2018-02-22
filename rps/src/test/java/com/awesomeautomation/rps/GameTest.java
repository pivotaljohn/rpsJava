package com.awesomeautomation.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.print.Paper;

import static com.awesomeautomation.rps.Throw.PAPER;
import static com.awesomeautomation.rps.Throw.ROCK;
import static com.awesomeautomation.rps.Throw.SCISSORS;
import static org.mockito.Mockito.*;

class GameTest {

	private Game game;

	private UserInterface userInterface;

	@BeforeEach
	void setUpGame() {
		game = new Game();
		userInterface = mock(UserInterface.class);
	}

	@Nested
	@DisplayName("Player One throws \"Rock\"")
	class PlayerOneThrowsRock {

		@Test
		@DisplayName("Player Two throws \"Scissors\", Player One Wins")
		void playerTwoThrowsScissors() {
			game.runGame(ROCK, SCISSORS, userInterface);

			verify(userInterface).playerOneWins();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Paper\", Player Two Wins")
		void playerTwoThrowsPaper() {
			game.runGame(ROCK, PAPER, userInterface);

			verify(userInterface).playerTwoWins();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Rock\", Draw")
		void playerTwoThrowsRock() {
			game.runGame(ROCK, ROCK, userInterface);

			verify(userInterface).draw();
			verifyNoMoreInteractions(userInterface);
		}
	}

	@Nested
	@DisplayName("Player One throws \"Paper\"")
	class PlayerOneThrowsPaper {

		@Test
		@DisplayName("Player Two throws \"Scissors\", Player Two Wins")
		void playerTwoThrowsScissors() {
			game.runGame(PAPER, SCISSORS, userInterface);

			verify(userInterface).playerTwoWins();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Paper\", Draw")
		void playerTwoThrowsPaper() {
			game.runGame(PAPER, PAPER, userInterface);

			verify(userInterface).draw();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Rock\", Player One Wins")
		void playerTwoThrowsRock() {
			game.runGame(PAPER, ROCK, userInterface);

			verify(userInterface).playerOneWins();
			verifyNoMoreInteractions(userInterface);
		}
	}

	@Nested
	@DisplayName("Player One throws \"Scissors\"")
	class PlayerOneThrowsScissors{

		@Test
		@DisplayName("Player Two throws \"Scissors\", Draw")
		void playerTwoThrowsScissors() {
			game.runGame(SCISSORS, SCISSORS, userInterface);

			verify(userInterface).draw();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Paper\", Player One Wins")
		void playerTwoThrowsPaper() {
			game.runGame(SCISSORS, PAPER, userInterface);

			verify(userInterface).playerOneWins();
			verifyNoMoreInteractions(userInterface);
		}

		@Test
		@DisplayName("Player Two throws \"Rock\", Player Two Wins")
		void playerTwoThrowsRock() {
			game.runGame(SCISSORS, ROCK, userInterface);

			verify(userInterface).playerTwoWins();
			verifyNoMoreInteractions(userInterface);
		}
	}

}
