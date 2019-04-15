package com.gaonsoft.ims.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.ims.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "sample")
@RequestMapping("/api")
public class TokenApiController extends BaseApiController {

	private static Logger logger = LoggerFactory.getLogger(TokenApiController.class);

	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "token", notes = "token 생성", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "userId", required = true, dataType = "int", paramType = "query", value = ""),
			@ApiImplicitParam(name = "projectId", required = true, dataType = "int", paramType = "query", value = ""),
			@ApiImplicitParam(name = "fixedVersionId", required = true, dataType = "int", paramType = "query", value = "")
	})
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity<?> createToken(
			@RequestParam(value = "userId") int userId,
			@RequestParam(value = "projectId") int projectId,
			@RequestParam(value = "fixedVersionId") int fixedVersionId) {
		Map<String, Object> result = new HashMap<>();
		try {
			Claims claims = Jwts.claims();
			claims.put("projectId", projectId);
    		claims.put("fixedVersionId", fixedVersionId);
    		claims.put("userId", userId);
			result.put("token", tokenService.createUnExpiredApiToken(claims));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
