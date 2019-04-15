package com.gaonsoft.ims.api.client;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Http {
	protected OkHttpClient client = null;

	private static final String HOST = "http://localhost:6920/api/";
	
	public Http() {
		this.client = new OkHttpClient();
	}

	protected String get(Headers headers, String url) {
		Request request = new Request.Builder()
				.url(HOST + url)
				.headers(headers)
				.build();
		Response response = null;
		ResponseBody responseBody = null;
		String body = null;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()) {
				responseBody = response.body();
				body = responseBody.string();
			}
			return body;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(responseBody != null) {
				responseBody.close();
			}
			if(response != null) {
				response.close();
			}
		}
	}
	
	protected String post(Headers headers, String url, RequestBody requestBody) {
		Request request = new Request.Builder()
				.url(HOST + url)
				.headers(headers)
				.post(requestBody)
				.build();
		Response response = null;
		ResponseBody responseBody = null;
		String body = null;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()) {
				responseBody = response.body();
				body = responseBody.string();
			}
			return body;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(responseBody != null) {
				responseBody.close();
			}
			if(response != null) {
				response.close();
			}
		}
	}
}
