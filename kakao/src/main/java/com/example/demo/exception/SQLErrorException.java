package com.example.demo.exception;
public class SQLErrorException extends ServerErrorException{
	public SQLErrorException()
	{
		super();
	}
	public SQLErrorException(String msg)
	{
		super(msg);
	}
}
