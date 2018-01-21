package com.webapp.ediya.core;

public class RestApiResponse {
	public int status;
	public int ecode;
	public String edesc;
	public Object response;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getEcode() {
		return ecode;
	}
	public void setEcode(int ecode) {
		this.ecode = ecode;
		this.edesc = AppConstants.API_ERROR_DESCRIPTION[ecode];
	}
	public String getEdesc() {
		return edesc;
	}
	public void setEdesc(String edesc) {
		this.edesc = edesc;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}
