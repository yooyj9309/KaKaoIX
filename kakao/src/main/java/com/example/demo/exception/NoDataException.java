package com.example.demo.exception;

public class NoDataException extends InvalidInputException{
	public NoDataException()
	{
		super();
	}
	public NoDataException(String msg)
	{
		super(msg);
	}
}
