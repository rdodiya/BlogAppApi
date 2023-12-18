package com.blogs.app.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{

	private HttpStatus status;
	private String message;

	
	public BlogApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogApiException(HttpStatus status,String message) {
		super(message);
		this.message=message;
		this.status=status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
}
