package com.gaonsoft.ims.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.ims.api.vo.RequestPostIssue;
import com.gaonsoft.ims.service.IssueService;
import com.gaonsoft.ims.vo.Issue;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "sample")
@RequestMapping("/api")
public class IssueApiController extends BaseApiController {

	private static Logger logger = LoggerFactory.getLogger(IssueApiController.class);

	@Autowired
	private IssueService issueService;

	@ApiOperation(value = "issues", notes = "이슈목록 조회", httpMethod = "GET", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page.") })
	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveIssues(
			@RequestHeader(value = "Authorization") String token,
			@RequestParam(name = "projectId", required = false) Integer projectId,
			@RequestParam(name = "fixedVersionId", required = false) Integer fixedVersionId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		try {
			if(projectId != null && fixedVersionId != null) {
				params.put("projectId", projectId);
				params.put("fixedVersionId", fixedVersionId);
			} else {
				Claims claims = getTokenValue(token);
				params.put("projectId", Integer.parseInt(claims.get("projectId").toString()));
				params.put("fixedVersionId", Integer.parseInt(claims.get("fixedVersionId").toString()));
				params.put("authorId", Integer.parseInt(claims.get("userId").toString()));
			}
			
			if(page != null && size != null) {
				result.put("result", issueService.getIssues(params, page, size));
			} else {
				result.put("result", issueService.getIssues(params));
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "issues", notes = "이슈 저장", httpMethod = "POST", produces = "application/json", consumes = "application/json", protocols = "http", hidden = false)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true, dataType = "string", paramType = "header"),
	})
	@RequestMapping(value = "/issue", method = RequestMethod.POST)
	public ResponseEntity<?> createIssue(
			@RequestHeader(value = "Authorization") String token,
			@RequestBody RequestPostIssue param) {
		Map<String, Object> result = new HashMap<>();
		try {
			Claims claims = getTokenValue(token);
			
			Issue issue = new Issue();
			issue.setTrackerId(param.getTrackerId());
			issue.setSubject(param.getSubject());
			issue.setDescription(param.getDescription());
			issue.setProjectId(Integer.parseInt(claims.get("projectId").toString()));
			issue.setFixedVersionId(Integer.parseInt(claims.get("fixedVersionId").toString()));
//			issue.setAuthorId(Integer.parseInt(claims.get("userId").toString())); // 21:cs로 고정
			issue.setAuthorId(21); // 21:cs로 고정
			
			result.put("executed", issueService.createIssue(issue));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
