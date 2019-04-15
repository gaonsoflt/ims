package com.gaonsoft.ims.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gaonsoft.ims.security.jwt.JwtTokenProvider;

import io.jsonwebtoken.Claims;

public class BaseApiController {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	protected Claims getTokenValue(String token) {
		return jwtTokenProvider.getTokenClaims(jwtTokenProvider.getJwtToken(token));
	}
}
