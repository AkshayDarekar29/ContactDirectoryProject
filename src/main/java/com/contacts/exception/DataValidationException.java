package com.contacts.exception;

/**
 * @author Akshay Darekar
 * @descrition  to throw our own custom exception
 */
public class DataValidationException extends Exception{
	
	private String errorMessage;
	
	public DataValidationException(){
		
	}
	
	public DataValidationException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
