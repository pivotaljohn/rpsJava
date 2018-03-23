package com.awesomeautomation.rps;

import java.util.Arrays;

public enum Throw {

	ROCK, PAPER, SCISSORS;

	public static boolean isAThrow(String value) {
		return Arrays.stream(values()).map(Enum::name)
				.anyMatch(aThrow -> value.toUpperCase().equals(aThrow));
	}

}
