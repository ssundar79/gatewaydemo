package com.demo.component;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.demo.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	private static final Logger log = LogManager.getLogger(AuthenticationFilter.class);
	public AuthenticationFilter() { super(Config.class);}
	
	@Autowired
	TokenService tokenService;
	
	@Override
	public GatewayFilter apply(Config config) {
		log.info("in gateway filter");
		
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			HttpHeaders headers = request.getHeaders();
			Optional<String> headerToken = Optional.ofNullable(headers.get("x-auth-token")).map(strings -> strings.get(0));
			Optional<String> userId = Optional.ofNullable(headers.get("userId")).map(strings -> strings.get(0));
			
			if(headerToken.isPresent() && userId.isPresent()) {
				try {
					return validateToken(headerToken.get(),userId.get())
							.flatMap(validToken -> {
								if(!validToken) {
									return this.onError(exchange,"Invalid token found in the header");
								}
								return chain.filter(exchange);
							});
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				return this.onError(exchange,"No authorization header");
			}
			return null;
			
		};
	}
	
	
	private Mono<Void> onError(ServerWebExchange exchange, String errorMsg){
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		response.getHeaders().add("errorMessage", errorMsg);
		return response.setComplete();
	}

	public static class Config{
		//put the config properties 
		
	}
	
	public Mono<Boolean> validateToken(String token, String userId) throws JsonProcessingException {
		return tokenService.validateToken(token);
		
	}
	
	
}
