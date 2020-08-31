package com.roulette.app.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.domain.Roulette;
import com.roulette.app.model.service.RouletteService;

@RestController
public class RouletteController {

	@Autowired
	private RouletteService rouletteService;

	private final AtomicLong counterRoulleteId = new AtomicLong();

	@PostMapping("/testRoullete")
	public ResponseEntity<String> createRouletteWithJson(@RequestBody Roulette roulette) {
		boolean result = rouletteService.createRoulette(roulette);
		if (result)
			return ResponseEntity.ok(roulette.getId().toString());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/newRoullete")
	public ResponseEntity<Long> createNewRoulette() {
		boolean result = rouletteService.createNewRoulette(counterRoulleteId.incrementAndGet());
		if (result)
			return ResponseEntity.ok(counterRoulleteId.get());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/getAllRoulettes")
	public ResponseEntity<List<Roulette>> fetchAllRoulettes() {
		List<Roulette> roulettes;
		roulettes = rouletteService.fetchAllRoulettes();
		return ResponseEntity.ok(roulettes);
	}

	@PostMapping("/openRoulette")
	public ResponseEntity<String> openRouletteById(@RequestBody Roulette roulette) {
		boolean result = rouletteService.openRouletteById(roulette.getId());
		if (result)
			return ResponseEntity.ok("Roulette opened successfully.");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping("/closeRoulette")
	public ResponseEntity<List<Bet>> closeRouletteShowResults(@RequestBody Roulette roulette) {
		List<Bet> bets;
		bets = rouletteService.closeRouletteShowResults(roulette.getId());
		return ResponseEntity.ok(bets);
	}

}
