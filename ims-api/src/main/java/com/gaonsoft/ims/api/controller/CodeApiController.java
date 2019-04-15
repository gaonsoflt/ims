package com.gaonsoft.ims.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.ims.service.CodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "sample")
@RequestMapping("/api/code")
public class CodeApiController extends BaseApiController {

	private static Logger logger = LoggerFactory.getLogger(CodeApiController.class);

	@Autowired
	private CodeService codeService;

	@ApiOperation(value = "version code", notes = "version code 조회", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "projectId", value = "project id", required = true, dataType = "int", paramType = "path"),
	})
	@RequestMapping(value = "/projects/{projectId}/versions", method = RequestMethod.GET)
	public ResponseEntity<?> getFixedVerionCode(
			@PathVariable(value = "projectId") int projectId) {
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("result", codeService.getFixedVersionCodeList(projectId));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "tracker code", notes = "이슈 타입코드 조회", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
	})
	@RequestMapping(value = "/trackers", method = RequestMethod.GET)
	public ResponseEntity<?> getTrackerCode() {
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("result", codeService.getTrackerCodeList());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "project code", notes = "프로젝트 조회", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
	})
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ResponseEntity<?> getProjectCode() {
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("result", codeService.getProjectCodeList());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
