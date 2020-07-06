package com.contacts.bean;

import java.util.List;

/**
 * @author Akshay Darekar
 * @description
 * 		1. In future, if we need to add any extra parameter in the response then we can easily add to ListOfContactsResponse
 * 
 */
public class ListOfContactsResponse {
	private List<Contact> listOfContacts;
	public ListOfContactsResponse() {
		
	}
	public List<Contact> getListOfContacts() {
		return listOfContacts;
	}
	public void setListOfContacts(List<Contact> listOfContacts) {
		this.listOfContacts = listOfContacts;
	}
}
