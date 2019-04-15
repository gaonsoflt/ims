package com.gaonsoft.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
	
	/**
	 * user 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveUsers(Map<String, Object> params) throws Exception;
}
