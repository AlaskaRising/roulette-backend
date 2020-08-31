package com.roulette.app.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.app.model.domain.Bet;
import com.roulette.app.model.service.BetService;

@RestController
public class BetController {

	@Autowired
	private BetService betService;

	private final AtomicLong counterBetId = new AtomicLong();

	@PostMapping("/placeBet")
	public ResponseEntity<String> placeABet(@RequestBody Bet betValues) {
		boolean result = betService.placeABet(counterBetId.incrementAndGet(), betValues);
		if (result)
			return ResponseEntity.ok("Bet placed successfully.");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
