package com.roulette.app.repository;

import java.util.List;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.domain.Roulette;

public interface RouletteDao {

	boolean createRoulette(Roulette roulette);

	boolean createNewRoulette(long id);

	List<Roulette> fetchAllRoulettes();

	boolean openRouletteById(Long id);

	List<Bet> closeRouletteShowResults(Long id);

}
