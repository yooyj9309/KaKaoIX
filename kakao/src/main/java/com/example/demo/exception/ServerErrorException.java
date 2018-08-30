package com.example.demo.exception;


public class ServerErrorException extends RuntimeException {
	public ServerErrorException()
	{
		super();
	}
	public ServerErrorException(String msg)
	{
		super(msg);
	}
}
