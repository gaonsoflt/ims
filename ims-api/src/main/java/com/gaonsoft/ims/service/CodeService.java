package com.gaonsoft.ims.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

	/**
	 * 프로젝트별 상세버전 정보 조회
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getFixedVersionCodeList(int projectId) throws Exception;

	/**
	 * 이슈구분 코드 정보 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTrackerCodeList() throws Exception;

	/**
	 * 프로젝트 코드 정보 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProjectCodeList() throws Exception;
}
