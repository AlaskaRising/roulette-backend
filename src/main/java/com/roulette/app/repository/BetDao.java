package com.roulette.app.repository;

import com.roulette.app.model.domain.Bet;

public interface BetDao {

	boolean placeABet(long betId, Bet betValues);

}
