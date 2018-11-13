package org.sooyoungkim.codingtest.pojo;

import org.json.simple.JSONObject;

public class Result {
	String status;
	JSONObject data;

	public Result(Boolean isSuccessful, JSONObject data) {
		if (isSuccessful) {
			this.setStatus("successful");
		} else {
			this.setStatus("failed");
		}
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
}
