package com.demo.model;


import lombok.Data;

@Data
public class CreditResponseDTO {
	
	private Long transactionId;
	private String amount;
	private String transactionType;
	

}
