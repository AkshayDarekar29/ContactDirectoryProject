package com.contacts.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.contacts.bean.Contact;
import com.contacts.bean.ListOfContactsResponse;
import com.contacts.constant.Constant;
import com.contacts.constant.ContactStatus;
import com.contacts.exception.DataValidationException;
import com.contacts.service.ContactService;
import com.contacts.util.ContactValidator;
import com.sun.istack.NotNull;

/**
 * @author Akshay Darekar
 */
@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ContactValidator validator;  
	
	/**
	 * @param String status
	 * @returns the list of Active contacts if status is active
	 * @returns the list of Inactive contacts if status is inactive
	 * @returns the list of all contacts if status is null or any value other than active and inactive
	 */
	@GetMapping(value="contacts", produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ListOfContactsResponse getContacts(@RequestParam(required = false) String status){
		ListOfContactsResponse res = new ListOfContactsResponse();
		ContactStatus contactStatus = null;
		if(status != null) {
			if(status.equals("active")) {
				contactStatus = ContactStatus.ACTIVE;
			}else if(status.equals("inactive")) {
				contactStatus = ContactStatus.INACTIVE;
			}
		}
		List<Contact> list = contactService.getContacts(contactStatus);
		res.setListOfContacts(list);
		return res;
	}
	
	/**
	 * @description 
	 * 		1. fistName and phoneNumber fields are mandatory
	 * 		2. saves status to ACTIVE by default
	 * 		3. Saves the given Contact
	 * @param Contact contact
	 * @throws DataValidationException when firstName or PhoneNumber is Empty
	 * @returns 201 status code on success
	 */
	@PostMapping(value="contact", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void addContact(@RequestBody @NotNull Contact contact) throws DataValidationException {
		validator.validate(contact);
		contact.setStatus(ContactStatus.ACTIVE);
		contact.setCreationDate(new Date());
		contactService.addContact(contact);
	}
	
	/**
	 * @description
	 * 		1. If contactId is present, it modifies the contact 
	 * @param Contact contact
	 * @throws DataValidationException if contactId is not provided or contact with given contactId is not present
	 * @returns 200 Http Status Code on Succeful modification of given contact
	 */
	@PutMapping(value="contact", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public void modifyContact(@RequestBody @NotNull Contact contact) throws DataValidationException {
		if(contact.getContactId() == 0) {
			throw new DataValidationException(Constant.EMPTY_CONTACT_ID_ERROR);
		}
		contact.setModificationDate(new Date());
		contactService.modifyContact(contact);
	}
	
	/**
	 * @description
	 * 		1. If contactID is present, set the status of given contactId to INACTIVE 
	 * @param int ContactId
	 * @throws DataValidationException if contact with given contactId is not present
	 * @returns 200 Http Status code on success
	 */
	@DeleteMapping(value="contact/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void makeContactInactive(@PathVariable("id") int contactId) throws DataValidationException {
		contactService.makeContactInactive(contactId);
	}
	
	/**
	 * @description Deletes all INACTIVE contacts
	 * @returns 200 Status code on Successfull deletion of all inactive contacts
	 */
	@DeleteMapping(value="inactiveContacts")
	@ResponseStatus(HttpStatus.OK)
	public void deleteInactiveContacts() {
		contactService.deleteInactiveContacts();
	}
}
