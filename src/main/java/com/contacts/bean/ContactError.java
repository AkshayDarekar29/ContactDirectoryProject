package com.contacts.bean;

/**
 * @author Akshay Darekar
 * @desciption
 * 		1. This class is for sending custom ResponseBody in case of Exceptions
 */
public class ContactError{
	
	private String errorDescription;
	
	public ContactError() {
		
	}
	
	public ContactError(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	
}
