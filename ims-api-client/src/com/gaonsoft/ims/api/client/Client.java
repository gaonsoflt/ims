package com.gaonsoft.ims.api.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Client extends Http {

	private static String TOKEN;
	private static Client INSTANCE = null;
	private static boolean IS_LOG = true;
	
	private Headers headers = null;
	private ObjectMapper mapper = null;
	
	public static Client newInstance(String token) {
		Client.INSTANCE = new Client(token);
		return Client.INSTANCE;
	}

	public static Client getInstance() {
		return Client.INSTANCE;
	}
	
	public static void showLog(boolean isShow) {
		Client.IS_LOG = isShow;
	}
	
	private static void log(String log) {
		if(Client.IS_LOG) {
			System.out.println(log);
		}
	}

	private Client(String token) {
		Client.TOKEN = token;
		this.mapper = new ObjectMapper();
		this.headers = new Headers.Builder()
				.add("Authorization", "Bearer " + Client.TOKEN)
				.add("Content-Type", "application/json")
				.build();
	}

	public void postIssue(String title, String contents, int issueType, int bizType) {
		try {
			Map<String, Object> requestBody = new HashMap<String, Object>();
			requestBody.put("subject", title);
			requestBody.put("description", contents);
			requestBody.put("trackerId", issueType);
			requestBody.put("Id", bizType);
			log(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));
			String result = post(headers, "issue", RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(requestBody)));
			if(result != null) {
				Map<String, Object> data = mapper.readValue(result, new TypeReference<Map<String, Object>>() {});
				log(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> selectIssue(int page, int size) {
		try {
			String result = get(headers, "issues?page=" + page + "&size=" + size);
			if(result != null) {
				Map<String, Object> data = mapper.readValue(result, new TypeReference<Map<String, Object>>() {});
				log(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
				return (List<Map<String, Object>>) data.get("result");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Client client = Client.newInstance("eyJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0SWQiOjUsImZpeGVkVmVyc2lvbklkIjozLCJ1c2VySWQiOjIxLCJ1c2VybmFtZSI6ImNzIiwiaWF0IjoxNTUzNjc2MzkxfQ.zbqt6pycW2ohhwp2xPxF44QtY5T3vL7qjfox8ML6VbY");
		client.postIssue("테스트", "content!@#\n내용", 2, 1);
		client.selectIssue(0, 2);
	}
}
