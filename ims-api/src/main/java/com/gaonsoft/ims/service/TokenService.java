package com.gaonsoft.ims.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

	/**
	 * Api 호출용 무기한 토큰 생성
	 * @param claims
	 * @return
	 * @throws Exception
	 */
	public String createUnExpiredApiToken(Claims claims) throws Exception;
}
