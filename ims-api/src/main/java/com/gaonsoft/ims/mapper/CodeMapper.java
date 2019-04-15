package com.gaonsoft.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CodeMapper {
	
	/**
	 * fixed version code list 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveFixedVersionCodeList(Map<String, Object> params) throws Exception;

	/**
	 * tracker code list 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveTrackerCodeList(Map<String, Object> params) throws Exception;
	
	/**
	 * project code list 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveProjectCodeList(Map<String, Object> params) throws Exception;
}
