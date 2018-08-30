package com.example.demo.exception;

public class InputValueNullException extends Exception{

	public InputValueNullException(String msg)
	{
		super(msg);
	}
	 @Override
     public String getMessage() {
       return "InputValueNullException" + super.getMessage();
     }       
}
