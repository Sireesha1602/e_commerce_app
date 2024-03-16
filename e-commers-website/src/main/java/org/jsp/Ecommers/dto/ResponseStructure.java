package org.jsp.Ecommers.dto;

import org.jsp.Ecommers.model.Merchant;

public class ResponseStructure<T> {
	private String message;
	private T data;
	private int statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String merchant) {
		this.message = merchant;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
