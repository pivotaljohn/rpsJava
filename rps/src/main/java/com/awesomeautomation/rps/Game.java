package com.awesomeautomation.rps;

import static com.awesomeautomation.rps.Throw.*;

public class Game {

	public void runGame(Throw p1Throw, Throw p2Throw, UserInterface userInterface) {
		if (p1Throw.equals(p2Throw)) {
			userInterface.draw();
		} else if (playerOneDidWin(p1Throw, p2Throw)) {
			userInterface.playerOneWins();
		} else {
			userInterface.playerTwoWins();
		}
	}

	private boolean playerOneDidWin(Throw p1Throw, Throw p2Throw) {
		return p1Throw.equals(PAPER) && p2Throw.equals(ROCK) ||
			p1Throw.equals(SCISSORS) && p2Throw.equals(PAPER) ||
			p1Throw.equals(ROCK) && p2Throw.equals(SCISSORS);
	}
}
