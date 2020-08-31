package com.roulette.app.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.repository.BetDao;

@Service
public class StandardBet implements BetService {

	@Autowired
	private BetDao betDao;
	
	@Override
	public boolean placeABet(long betId, Bet betValues) {
		return betDao.placeABet(betId, betValues);
	}

}
