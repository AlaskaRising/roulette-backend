package com.roulette.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.domain.Roulette;
import com.roulette.app.repository.RouletteDao;

@Service
public class StandardRoulette implements RouletteService {

	@Autowired
	private RouletteDao rouletteDao;

	@Override
	public boolean createRoulette(Roulette roulette) {
		return rouletteDao.createRoulette(roulette);
	}

	@Override
	public boolean createNewRoulette(long id) {
		return rouletteDao.createNewRoulette(id);
	}

	@Override
	public List<Roulette> fetchAllRoulettes() {
		return rouletteDao.fetchAllRoulettes();
	}

	@Override
	public boolean openRouletteById(Long id) {
		return rouletteDao.openRouletteById(id);
	}

	@Override
	public List<Bet> closeRouletteShowResults(Long id) {
		return rouletteDao.closeRouletteShowResults(id);
	}

}
