package com.demo;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.demo.controller.CreditController;
import com.demo.model.CreditRequestDTO;
import com.demo.model.CreditResponseDTO;
import com.demo.service.CreditService;

@WebFluxTest(CreditController.class)
class CreditControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@MockBean
	private CreditService creditService;
	
	@Test
	void testPerformCreditCalculation() {
		CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
		creditRequestDTO.setAmount("6000");
		creditRequestDTO.setTransactionId(100L);
		CreditResponseDTO creditResponseDTO = new CreditResponseDTO();
		creditResponseDTO.setAmount("6000");
		creditResponseDTO.setTransactionId(100L);
		creditResponseDTO.setTransactionType("CREDIT");
		
		when(creditService.performCreditCalculation(creditRequestDTO)).thenReturn(creditResponseDTO);
		webTestClient.post()
		.uri("/credit/test2")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.bodyValue(creditRequestDTO)
		.exchange()
		.expectStatus().isOk()
		.expectBody(CreditResponseDTO.class).isEqualTo(creditResponseDTO);
		
		

	}
	

}
