package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.service.BalanceService;

import io.swagger.v3.oas.annotations.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/balance")
public class BalanceController {

	private static final Logger log = LogManager.getLogger(BalanceController.class);
	@Autowired
	private BalanceService balanceService;
	
	@GetMapping("/test1")
	public Mono<ResponseEntity<String>> getRawMessage(@Parameter String value) {
		
		try {
			log.info("Executing getRawMessage");
			if(!value.equals("1")) {
				Mono.just(ResponseEntity.badRequest().body("Invalid Parameter"));
			}
			String rawMessage = balanceService.getRawMessage();
			return  Mono.just(ResponseEntity.ok().body(rawMessage));
			
		}
		catch (Exception e) {
			log.error("Execution failed",e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}


}
