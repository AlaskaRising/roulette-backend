package com.roulette.app.model.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Roulette implements Serializable {
	
	public static final int [] REDNUMBERS = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
	private Long id;
	private Boolean openBets;
	private Boolean closedGame;
	private int outcome;
	private String color;

	public Roulette(long id) {
		this.id = id;
		this.openBets = false;
		this.closedGame = false;
		this.outcome = -1;
		this.color = "";
	}

	public void spin() {
		Random rand = new Random();
		int randomNum = rand.nextInt(37);
		this.setOutcome(randomNum);
		if (randomNum == 0) {
			this.setColor("GREEN");
		}else if (Arrays.stream(REDNUMBERS).anyMatch(i -> i == randomNum)) {
			this.setColor("RED");
		}else {
			this.setColor("BLACK");
		}
		this.setClosedGame(true);
	}

}
