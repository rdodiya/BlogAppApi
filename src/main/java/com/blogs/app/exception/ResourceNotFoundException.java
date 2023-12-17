package com.blogs.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	private String fieldKey;
	private String fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldKey, String fieldValue) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldKey,fieldValue));
		this.resourceName = resourceName;
		this.fieldKey = fieldKey;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public String getFieldValue() {
		return fieldValue;
	}
	
}
