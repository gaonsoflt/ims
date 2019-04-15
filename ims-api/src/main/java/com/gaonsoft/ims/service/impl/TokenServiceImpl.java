package com.gaonsoft.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.ims.mapper.UserMapper;
import com.gaonsoft.ims.security.jwt.JwtTokenProvider;
import com.gaonsoft.ims.service.TokenService;

import io.jsonwebtoken.Claims;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String createUnExpiredApiToken(Claims claims) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", claims.get("userId"));
		List<Map<String, Object>> users = userMapper.retrieveUsers(params);
		if(!users.isEmpty()) {
			claims.put("username", users.get(0).get("username"));
		}
		return tokenProvider.createUnExpiredToken(claims);
	}

}
