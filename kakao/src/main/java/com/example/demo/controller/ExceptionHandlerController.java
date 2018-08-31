package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.dao.DuplicateKeyException;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NoAuthException;
import com.example.demo.exception.ServerErrorException;

@ControllerAdvice()
public class ExceptionHandlerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(value = InvalidInputException.class)
	@ResponseBody 
	public ResponseEntity<?> InvalidInputException(HttpServletRequest request, InvalidInputException ex) 
	{
		LOGGER.error("Exception type : " + ex.getClass().getName());
		LOGGER.error("Exception message : " + ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ServerErrorException.class)
	@ResponseBody 
	public ResponseEntity<?> ServerErrorException(HttpServletRequest request, ServerErrorException ex) 
	{
		LOGGER.error("Exception type : " + ex.getClass().getName());
		LOGGER.error("Exception message : " + ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = DuplicateKeyException.class)
	@ResponseBody 
	public ResponseEntity<?> DuplicateKeyException(HttpServletRequest request, DuplicateKeyException ex) 
	{
		LOGGER.error("Exception type : " + ex.getClass().getName());
		LOGGER.error("Exception message : " + ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NoAuthException.class)
	@ResponseBody 
	public ResponseEntity<?> NoAuthExceptoin(HttpServletRequest request, NoAuthException ex) 
	{
		LOGGER.error("Exception type : " + ex.getClass().getName());
		LOGGER.error("Exception message : " + ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.FORBIDDEN);
	}
	
}
