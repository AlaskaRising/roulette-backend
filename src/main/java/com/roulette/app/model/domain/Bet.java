package com.roulette.app.model.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bet implements Serializable {

	private Long id;
	private Long idUser;
	private Long idRoulette;
	private double betAmount;
	private double amountEarned;
	private String betChoise;
	private boolean betClaimed;

	public Bet(long betId, Bet betValues) {
		this.id = betId;
		this.idUser = betValues.getIdUser();
		this.idRoulette = betValues.getIdRoulette();
		this.betAmount = betValues.getBetAmount();
		this.amountEarned = -1;
		this.betChoise = betValues.getBetChoise();
		this.betClaimed = false;
	}

	public void calculateProfit(int outcome, String color) {
		if(!color.equals("GREEN") && (betChoise.equals("RED") || betChoise.equals("BLACK"))) {
				this.amountEarned = (betChoise.equals(color))? this.betAmount * 2 : 0;
		}else if (Integer.parseInt(betChoise) == outcome) {
			this.amountEarned = this.betAmount * 36;
		}else {
			this.amountEarned = 0;
		}	
	}
}
