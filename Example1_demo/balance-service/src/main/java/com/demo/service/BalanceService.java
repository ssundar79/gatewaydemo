package com.demo.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.controller.BalanceController;



@Component
public class BalanceService {
	private static final Logger log = LogManager.getLogger(BalanceService.class);
	
	public String getRawMessage() {
		log.info("Executing getRawMessage in BalanceService");
		String rawXmlMessage ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<note>\r\n" + 
				"  <to>totest</to>\r\n" + 
				"  <from>fromtest</from>\r\n" + 
				"  <heading>Balance</heading>\r\n" + 
				"  <body>100$</body>\r\n" + 
				"</note>";
		return rawXmlMessage;
	}

}
