package com.roulette.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.domain.Roulette;

@Repository
public class RedisBet implements BetDao {

	@Autowired
	private RedisTemplate redisTemplate;
	private static final String KEY = "BET";
	private static final String KEYR = "ROULETTE";

	@Override
	public boolean placeABet(long betId, Bet betValues) {
		if (checkRouletteState(betValues.getIdRoulette())) {
			Bet newBet = new Bet(betId, betValues);
			try {
				redisTemplate.opsForHash().put(KEY, newBet.getId().toString(), newBet);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	private boolean checkRouletteState(Long id) {
		Roulette roulette;
		try {
			roulette = (Roulette) redisTemplate.opsForHash().get(KEYR, id.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return roulette.getOpenBets() && !roulette.getClosedGame();
	}
}
