package com.contacts.service;

import java.util.List;
import com.contacts.bean.Contact;
import com.contacts.constant.ContactStatus;
import com.contacts.exception.DataValidationException;
/**
 * @author Akshay Darekar
 */
public interface ContactService {
	public List<Contact> getContacts(ContactStatus contactStatus);
	public void addContact(Contact contact);
	public void modifyContact(Contact contact) throws DataValidationException;
	public void makeContactInactive(int contactId) throws DataValidationException;
	public void deleteInactiveContacts();
}
