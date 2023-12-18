package com.blogs.app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetailsDto {

	private Date timestamp;
	private String message;
	private String details;

}
