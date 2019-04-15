package com.gaonsoft.ims.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "sample")
@RequestMapping("/api/test")
public class TestApiController {

	private static Logger logger = LoggerFactory.getLogger(TestApiController.class);

	@ApiOperation(value = "OpenApiTest", notes = "인증이 필요없는 Api", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({})
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/open/get", method = RequestMethod.GET)
	public ResponseEntity<?> openGet() {
		return new ResponseEntity<>("openGet", HttpStatus.OK);
	}

	@ApiOperation(value = "ApiTest", notes = "인증이 필요한 Api", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"), })
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized") })
	public ResponseEntity<?> get(@RequestHeader(value = "Authorization") String token) {
		logger.debug(token);
		return new ResponseEntity<>("get", HttpStatus.OK);
	}
}
