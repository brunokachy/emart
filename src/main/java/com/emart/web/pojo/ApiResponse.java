package com.emart.web.pojo;

/**
 * @author Bruno Okafor 2019-11-20
 */
public class ApiResponse<T> {

	private String message;

	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
