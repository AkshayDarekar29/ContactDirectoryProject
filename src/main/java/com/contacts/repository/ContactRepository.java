package com.contacts.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.contacts.bean.Contact;
import com.contacts.constant.ContactStatus;

/**
 * @author Akshay Darekar
 * 
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{
	/**
	 * @description This method return the List of contacts with given contactStatus
	 * @param ContactStatus status
	 * @return List of Contacts
	 */
	public List<Contact> findAllByStatus(ContactStatus status);
}
