package com.contacts.util;

import org.springframework.stereotype.Component;
import com.contacts.bean.Contact;
/**
 * @author Akshay Darekar
 *
 */
@Component
public class ContactModifier {
	/**
	 * @param Contact newContact
	 * @param Contact oldContact
	 * @return oldContact with overrided values from newContact Object which are @notNull
	 */
	public Contact modify(Contact newContact, Contact oldContact) {
		if(oldContact != null) {
			if(newContact.getFirstName() != null) {
				oldContact.setFirstName(newContact.getFirstName());
			}
			if(newContact.getLastName() != null) {
				oldContact.setLastName(newContact.getLastName());
			}
			if(newContact.getEmail() != null) {
				oldContact.setEmail(newContact.getEmail());
			}
			if(newContact.getPhoneNumber() != null) {
				oldContact.setPhoneNumber(newContact.getPhoneNumber());
			}
			if(newContact.getCreationDate() != null) {
				oldContact.setCreationDate(newContact.getCreationDate());
			}
			if(newContact.getModificationDate() != null) {
				oldContact.setModificationDate(newContact.getModificationDate());
			}
			if(newContact.getStatus() != null) {
				oldContact.setStatus(newContact.getStatus());
			}
		}
		return oldContact;
	}
}
