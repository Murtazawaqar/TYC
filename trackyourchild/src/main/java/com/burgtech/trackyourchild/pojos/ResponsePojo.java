package com.burgtech.trackyourchild.pojos;

import org.springframework.stereotype.Component;

@Component
public class ResponsePojo 
{
	private String code;
	private String message;
	private String value;
	
	public ResponsePojo()
	{
		
	}
	
	public ResponsePojo(String code, String message, String value)
	{
		this.code = code;
		this.message = message;
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
