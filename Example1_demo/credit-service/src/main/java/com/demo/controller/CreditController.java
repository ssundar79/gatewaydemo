package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.model.CreditRequestDTO;
import com.demo.model.CreditResponseDTO;
import com.demo.service.CreditService;

import io.swagger.v3.oas.annotations.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/credit")
public class CreditController {

	private static final Logger log = LogManager.getLogger(CreditController.class);
	@Autowired
	private CreditService creditService;
	
	@PostMapping(value ="/test2")
	public Mono<ResponseEntity<CreditResponseDTO>> performCredit(@Parameter(description="credit dto",required=true) 
																@RequestBody CreditRequestDTO creditRequestDTO)
	{
	
		try {
			log.info("Executing performCredit");
	
			CreditResponseDTO creditResponseDTO = creditService.performCreditCalculation(creditRequestDTO);
			return  Mono.just(ResponseEntity.ok().body(creditResponseDTO));
			
		}
		catch (Exception e) {
			log.error("Execution failed",e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}


}
