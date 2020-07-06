package com.contacts.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.contacts.constant.ContactStatus;
import com.sun.istack.NotNull;
/**
 * @author Akshay Darekar
 * @description
 * 		1. contactId is auto generated
 * 		2. firstName and phoneNumber are mandatory
 * 		3. status can have value as ACTIVE or INACTIVE
 */
@Entity
public class Contact {
	@Id
	@GeneratedValue
	private int contactId;
	@NotNull
	private String firstName;
	private String lastName;
	private String email;
	@NotNull
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private ContactStatus status;
	
	private Date creationDate;
	private Date modificationDate;
	
	public Contact() {
		
	}
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ContactStatus getStatus() {
		return status;
	}

	public void setStatus(ContactStatus status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
}
