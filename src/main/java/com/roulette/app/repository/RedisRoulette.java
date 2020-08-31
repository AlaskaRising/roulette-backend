package com.roulette.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.domain.Roulette;

@Repository
public class RedisRoulette implements RouletteDao {

	@Autowired
	private RedisTemplate redisTemplate;
	private static final String KEY = "ROULETTE";
	private static final String KEYBET = "BET";

	@Override
	public boolean createRoulette(Roulette roulette) {
		try {
			redisTemplate.opsForHash().put(KEY, roulette.getId().toString(), roulette);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createNewRoulette(long id) {
		Roulette newRoulette = new Roulette(id);
		try {
			redisTemplate.opsForHash().put(KEY, newRoulette.getId().toString(), newRoulette);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Roulette> fetchAllRoulettes() {
		List<Roulette> roulettes;
		roulettes = redisTemplate.opsForHash().values(KEY);
		return roulettes;
	}

	@Override
	public boolean openRouletteById(Long id) {
		Roulette roulette;
		try {
			roulette = (Roulette) redisTemplate.opsForHash().get(KEY, id.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return openBetIfExist(roulette);
	}

	private boolean openBetIfExist(Roulette roulette) {
		if (roulette != null) {
			if (roulette.getOpenBets() == false) {
				roulette.setOpenBets(true);
				redisTemplate.opsForHash().put(KEY, roulette.getId().toString(), roulette);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Bet> closeRouletteShowResults(Long id) {
		List<Bet> betsTotal;
		betsTotal = redisTemplate.opsForHash().values(KEYBET);
		Roulette roulette = spinRoulette(id);
		return filterBets(id, betsTotal, roulette.getOutcome(), roulette.getColor());
	}

	private Roulette spinRoulette(Long id) {
		Roulette roulette;
		roulette = (Roulette) redisTemplate.opsForHash().get(KEY, id.toString());
		roulette.spin();
		redisTemplate.opsForHash().put(KEY, roulette.getId().toString(), roulette);
		return roulette;
	}

	private void saveProfit(Bet bet, int outcome, String color) {
		bet.calculateProfit(outcome, color);
		redisTemplate.opsForHash().put(KEYBET, bet.getId().toString(), bet);
	}

	private List<Bet> filterBets(Long id, List<Bet> bet, int outcome, String color) {
		List<Bet> betsFilterId = new ArrayList<>();
		for (Bet itemBet : bet) {
			if (itemBet.getIdRoulette().equals(id)) {
				saveProfit(itemBet, outcome, color);
				betsFilterId.add(itemBet);
			}
		}
		return betsFilterId;
	}

}
