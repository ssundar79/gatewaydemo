package com.demo.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.demo.model.CreditRequestDTO;
import com.demo.model.CreditResponseDTO;

import lombok.NonNull;



@Component
public class CreditService {
	private static final Logger log = LogManager.getLogger(CreditService.class);
	
	public CreditResponseDTO performCreditCalculation(CreditRequestDTO creditRequestDTO) {
		log.info("Executing performCreditCalculation in CreditService");
		CreditResponseDTO creditResponseDTO = new CreditResponseDTO();
		creditResponseDTO.setAmount(creditRequestDTO.getAmount());
		creditResponseDTO.setTransactionId(creditRequestDTO.getTransactionId());	
		creditResponseDTO.setTransactionType(getDebitCredit(creditRequestDTO.getAmount()));	
		return creditResponseDTO;
	}
	
	public static String getDebitCredit(@NonNull String amount)
	{
	double amountValue = Double.parseDouble(amount);
	return amountValue < 0? "DEBIT" : "CREDIT";
	}

}
