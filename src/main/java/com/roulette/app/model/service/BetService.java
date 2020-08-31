package com.roulette.app.model.service;

import com.roulette.app.model.domain.Bet;

public interface BetService {

	boolean placeABet(long betId, Bet betValues);

}
