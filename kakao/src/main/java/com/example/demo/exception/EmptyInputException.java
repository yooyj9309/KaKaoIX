package com.example.demo.exception;

public class EmptyInputException extends InvalidInputException{
	public EmptyInputException()
	{
		super();
	}
	public EmptyInputException(String msg)
	{
		super(msg);
	}
}
