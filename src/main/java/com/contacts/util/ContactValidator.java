package com.contacts.util;

import org.springframework.stereotype.Component;
import com.contacts.bean.Contact;
import com.contacts.constant.Constant;
import com.contacts.exception.DataValidationException;
/**
 * @author Akshay Darekar
 */
@Component
public class ContactValidator {
	/**
	 * @param Contact contact
	 * @throws DataValidationException when firstName or phoneNumber is @null
	 */
	public void validate(Contact contact) throws DataValidationException {
		if(contact.getFirstName() == null) {
			throw new DataValidationException(Constant.EMPTY_FIRST_NAME_ERROR);
		}else if(contact.getPhoneNumber() == null) {
			throw new DataValidationException(Constant.EMPTY_PHONE_NUMBER_ERROR);
		}
	}
}
