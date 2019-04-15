package com.gaonsoft.ims.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.ims.mapper.IssueMapper;
import com.gaonsoft.ims.service.IssueService;
import com.gaonsoft.ims.vo.Issue;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueMapper issueMapper;
	
	@Override
	public List<Map<String, Object>> getIssues(Map<String, Object> params) throws Exception {
		return issueMapper.retrieveIssuesList(params);
	}

	@Override
	public List<Map<String, Object>> getIssues(Map<String, Object> params, int page, int size) throws Exception {
		RowBounds rowBounds = new RowBounds(page, size);
		return issueMapper.retrieveIssuesList(params, rowBounds);
	}

	@Override
	public int createIssue(Issue issue) throws Exception {
		int rootId = issueMapper.retrieveNextIssueId();
		issue.setRootId(rootId);
		return issueMapper.insertIssue(issue);
	}

}
