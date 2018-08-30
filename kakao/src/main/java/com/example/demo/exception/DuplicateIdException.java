package com.example.demo.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateIdException extends DuplicateKeyException{
	
	public DuplicateIdException(String msg)
	{
		super(msg);
	}
}
