package com.demo.service;

import java.util.Base64;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Mono;

@Service
public class TokenService {
	 public String secretKey="secret-key";
	 @PostConstruct
	    protected void init() {
	        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	    }

	
	public Mono<Boolean> validateToken(String token) throws JwtException,IllegalArgumentException{
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return Mono.just(true);
}
}
