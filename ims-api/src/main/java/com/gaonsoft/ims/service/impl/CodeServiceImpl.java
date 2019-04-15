package com.gaonsoft.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.ims.mapper.CodeMapper;
import com.gaonsoft.ims.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeMapper codeMapper;

	@Override
	public List<Map<String, Object>> getFixedVersionCodeList(int projectId) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("projectId", projectId);
		return codeMapper.retrieveFixedVersionCodeList(param);
	}

	@Override
	public List<Map<String, Object>> getTrackerCodeList() throws Exception {
		return codeMapper.retrieveTrackerCodeList(null);
	}

	@Override
	public List<Map<String, Object>> getProjectCodeList() throws Exception {
		return codeMapper.retrieveProjectCodeList(null);
	}

}
