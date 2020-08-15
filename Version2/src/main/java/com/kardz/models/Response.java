package com.kardz.models;


public class Response {
	

	private String response;
	private String statusCode;
	private Boolean isSuccessFul;
	private Integer responseCode;
	
	public Response(String response, String statusCode, Boolean isSuccessful, int responseCode) {
		this.response =response;
		this.statusCode =statusCode;
		this.isSuccessFul = isSuccessful;
		this.responseCode = responseCode;
		
	}
	
	public Response(){
		
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Boolean getIsSuccessFul() {
		return isSuccessFul;
	}
	public void setIsSuccessFul(Boolean isSuccessFul) {
		this.isSuccessFul = isSuccessFul;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

}
