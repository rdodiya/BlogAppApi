package com.blogs.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.blogs.app.dto.ErrorDetailsDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle Specific Exception 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetailsDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException
			,WebRequest  webRequest){
		ErrorDetailsDto detailsDto=new ErrorDetailsDto(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(true));
		return new ResponseEntity<ErrorDetailsDto>(detailsDto,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetailsDto> handleBlogApiException(BlogApiException blogApiException,WebRequest webRequest){
		ErrorDetailsDto errorDetailsDto=new ErrorDetailsDto(new Date(), blogApiException.getMessage(), webRequest.getDescription(true));
		return new ResponseEntity<ErrorDetailsDto>(errorDetailsDto,HttpStatus.BAD_REQUEST);
	}
	
	// Handle Global Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsDto> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(new Date(), exception.getMessage(), webRequest.getDescription(true));
		return new ResponseEntity<ErrorDetailsDto>(errorDetailsDto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
 	
}
