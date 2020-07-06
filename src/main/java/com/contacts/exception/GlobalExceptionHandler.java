package com.contacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.contacts.bean.ContactError;
/**
 * @author Akshay Darekar
 * @description GlobalException Handler Class to catch all exceptions at one place
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ContactError> throwContactValidationException(DataValidationException e) {
		ContactError contactError = new ContactError();
		contactError.setErrorDescription(e.getErrorMessage());
		return new ResponseEntity<ContactError>(contactError, HttpStatus.BAD_REQUEST);
	}
}
