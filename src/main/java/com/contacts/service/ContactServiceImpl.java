package com.contacts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contacts.bean.Contact;
import com.contacts.constant.Constant;
import com.contacts.constant.ContactStatus;
import com.contacts.exception.DataValidationException;
import com.contacts.repository.ContactRepository;
import com.contacts.util.ContactModifier;

/**
 * @author Akshay Darekar
 */
@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private ContactModifier contactModifier;
	
	public List<Contact> getContacts(ContactStatus contactStatus){
		List<Contact> listOfContacts = new ArrayList<>();
		if(contactStatus != null) {
			listOfContacts = contactRepo.findAllByStatus(contactStatus).stream().collect(Collectors.toList());
		}else {
			contactRepo.findAll().forEach(listOfContacts::add);
		}
		return listOfContacts;
	}
	
	public void addContact(Contact contact) {
		contactRepo.save(contact);
	}
	
	public void modifyContact(Contact contact) throws DataValidationException {
		Contact oldContact;
		try {
			oldContact = contactRepo.findById(contact.getContactId()).get();
		}catch(NoSuchElementException e){
			throw new DataValidationException(Constant.CONTACT_NOT_FOUND);
		}
		contact = contactModifier.modify(contact, oldContact);
		contactRepo.save(contact);
	}
	
	public void makeContactInactive(int contactId) throws DataValidationException {
		Contact contact;
		try {
			contact = contactRepo.findById(contactId).get();
		}catch(NoSuchElementException e){
			throw new DataValidationException(Constant.CONTACT_NOT_FOUND);
		}
		contact.setStatus(ContactStatus.INACTIVE);
		contactRepo.save(contact);
	}
	
	public void deleteInactiveContacts() {
		List<Contact> listOfInactiveContacts = getContacts(ContactStatus.INACTIVE);
		listOfInactiveContacts.forEach(contact -> contactRepo.deleteById(contact.getContactId()));
	}
}
