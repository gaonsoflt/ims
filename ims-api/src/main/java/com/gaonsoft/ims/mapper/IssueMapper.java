package com.gaonsoft.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.gaonsoft.ims.vo.Issue;

@Mapper
@Repository
public interface IssueMapper {
	/**
	 * issues table의 총 row 수
	 * @return
	 * @throws Exception
	 */
	int retrieveCountIssues() throws Exception;
	
	/**
	 * 다음번에 생성될 이슈의 id 값 가져오기
	 * @return
	 * @throws Exception
	 */
	int retrieveNextIssueId() throws Exception;
	
	/**
	 * issues table 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveIssuesList(Map<String, Object> params) throws Exception;
	
	/**
	 * issues table 조회(with page)
	 * @param params
	 * @param rowBounds
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> retrieveIssuesList(Map<String, Object> params, RowBounds rowBounds) throws Exception;
	
	/**
	 * issue 등록
	 * @param issue
	 * @return
	 * @throws Exception
	 */
	int insertIssue(Issue issue) throws Exception;
}
