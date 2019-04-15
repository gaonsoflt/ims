package com.gaonsoft.ims.service;

import java.util.List;
import java.util.Map;

import com.gaonsoft.ims.vo.Issue;

public interface IssueService {

	/**
	 * issues table 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getIssues(Map<String, Object> params) throws Exception;

	/**
	 * issues table 조회 (with page)
	 * @param params
	 * @param page 페이지 수
	 * @param size 한 페이지 출력 row
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getIssues(Map<String, Object> params, int page, int size) throws Exception;
	
	/**
	 * issue 생성
	 * @param issue
	 * @return
	 * @throws Exception
	 */
	public int createIssue(Issue issue) throws Exception;
}
