package com.demo;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.demo.controller.BalanceController;
import com.demo.service.BalanceService;

@WebFluxTest(BalanceController.class)
class BalanceControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@MockBean
	private BalanceService balanceService;
	
	@Test
	void testGetRawMessage() {
		
		when(balanceService.getRawMessage()).thenReturn("dummy");
		webTestClient.get()
		.uri("/balance/test1?value=1").exchange().expectStatus().isOk().expectBody(String.class);
	}
	@Test
	void testGetRawMessageWrongFail() {
		
		when(balanceService.getRawMessage()).thenReturn("dummy");
		webTestClient.get()
		.uri("/balance/test5").exchange().expectStatus().isNotFound();
	}

}
