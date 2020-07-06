package com.contacts;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.contacts.bean.Contact;
import com.contacts.bean.ContactError;
import com.contacts.constant.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
class ContactDirectoryApplicationTests {
	@LocalServerPort
    private int randomServerPort;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/*
	 * To test POST /contact rest call
	 * with empty firstName
	 */
	@Test
	public void addContactTestWithEmptyFirstName() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact";
		URI uri = new URI(restUrl);
		Contact contact = new Contact();
		contact.setPhoneNumber("9822154879");
		HttpEntity<Contact> request = new HttpEntity<>(contact);
		try {
			ResponseEntity<Void> result = restTemplate.postForEntity(uri, request, null);
			Assertions.assertEquals(201, result.getStatusCodeValue());
		}catch(HttpClientErrorException e) {
			Assertions.assertEquals(400, e.getRawStatusCode());
			Assertions.assertEquals(Constant.EMPTY_FIRST_NAME_ERROR, objectMapper.readValue(e.getResponseBodyAsString(), ContactError.class).getErrorDescription());
		}
	}
	
	/*
	 * To test POST /contact rest call
	 * with empty phoneNumber
	 */
	@Test
	public void addContactTestWithEmptyPhoneNumber() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact";
		URI uri = new URI(restUrl);
		Contact contact = new Contact();
		contact.setFirstName("Akshay");
		HttpEntity<Contact> request = new HttpEntity<>(contact);
		try {
			ResponseEntity<Void> result = restTemplate.postForEntity(uri, request, null);
			Assertions.assertEquals(201, result.getStatusCodeValue());
		}catch(HttpClientErrorException e) {
			Assertions.assertEquals(400, e.getRawStatusCode());
			Assertions.assertEquals(Constant.EMPTY_PHONE_NUMBER_ERROR, objectMapper.readValue(e.getResponseBodyAsString(), ContactError.class).getErrorDescription());
		}
	}
	
	/*
	 * To test POST /contact rest call
	 * with FirstName and PhoneNumber
	 */
	@Test
	public void addContactTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact";
		URI uri = new URI(restUrl);
		Contact contact = new Contact();
		contact.setFirstName("Akshay");
		contact.setPhoneNumber("9876543210");
		HttpEntity<Contact> request = new HttpEntity<>(contact);
		ResponseEntity<Void> result = restTemplate.postForEntity(uri, request, null);
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}
	

	/*
	 * To test GET /contacts rest call
	 * without status QueryParam
	 */
	@Test
	public void getAllContactsTest() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contacts";
		URI uri = new URI(restUrl);
		ResponseEntity<Void> result = restTemplate.getForEntity(uri, null);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	/*
	 * To test GET /contacts rest call
	 * with status=active QueryParam
	 */
	@Test
	public void getAllContactsWithActiveStatusTest() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contacts?status=active";
		URI uri = new URI(restUrl);
		ResponseEntity<Void> result = restTemplate.getForEntity(uri, null);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	/*
	 * To test GET /contacts rest call
	 * with status=inactive QueryParam
	 */
	@Test
	public void getAllContactsWithInactiveStatusTest() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contacts?status=inactive";
		URI uri = new URI(restUrl);
		ResponseEntity<Void> result = restTemplate.getForEntity(uri, null);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	/*
	 * To test PUT /contact rest call
	 * with empty contactId
	 */
	@Test
	public void modifyContactTestWithEmptyContactId() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact";
		URI uri = new URI(restUrl);
		Contact contact = new Contact();
		contact.setPhoneNumber("9822154879");
		HttpEntity<Contact> request = new HttpEntity<>(contact);
		try {
			restTemplate.put(uri, request);
		}catch(HttpClientErrorException e) {
			Assertions.assertEquals(400, e.getRawStatusCode());
			Assertions.assertEquals(Constant.EMPTY_CONTACT_ID_ERROR, objectMapper.readValue(e.getResponseBodyAsString(), ContactError.class).getErrorDescription());
		}
	}
	
	/*
	 * To test PUT /contact rest call
	 * with invalid contactId
	 */
	@Test
	public void modifyContactTestWithInvalidContactId() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact";
		URI uri = new URI(restUrl);
		Contact contact = new Contact();
		contact.setContactId(1);
		contact.setPhoneNumber("9822154879");
		HttpEntity<Contact> request = new HttpEntity<>(contact);
		try {
			restTemplate.put(uri, request);
		}catch(HttpClientErrorException e) {
			Assertions.assertEquals(400, e.getRawStatusCode());
			Assertions.assertEquals(Constant.CONTACT_NOT_FOUND, objectMapper.readValue(e.getResponseBodyAsString(), ContactError.class).getErrorDescription());
		}
	}
	
	/*
	 * To test PUT /contact rest call
	 * 
	 */
	/*
	 * @Test public void modifyContactTest() throws URISyntaxException,
	 * JsonMappingException, JsonProcessingException { addContactTest();
	 * RestTemplate restTemplate = new RestTemplate(); String restUrl = getBaseUrl()
	 * +"/contact"; URI uri = new URI(restUrl); Contact contact = new Contact();
	 * contact.setContactId(1); contact.setPhoneNumber("9822154879"); HttpHeaders
	 * header = new HttpHeaders(); List<MediaType> mediaTypes = new ArrayList<>();
	 * mediaTypes.add(MediaType.APPLICATION_JSON); header.setAccept(mediaTypes);
	 * HttpEntity<Contact> request = new HttpEntity<>(contact);
	 * restTemplate.put(uri, request); }
	 */
	
	/*
	 * To test DELETE /contact rest call
	 * with invalid contactId
	 */
	@Test
	public void deleteContactTestWithInvalidContactId() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact/1";
		URI uri = new URI(restUrl);
		try {
			restTemplate.delete(uri);
		}catch(HttpClientErrorException e) {
			Assertions.assertEquals(400, e.getRawStatusCode());
			Assertions.assertEquals(Constant.CONTACT_NOT_FOUND, objectMapper.readValue(e.getResponseBodyAsString(), ContactError.class).getErrorDescription());
		}
	}
	
	/*
	 * To test DELETE /contact rest call
	 */
	@Test
	public void deleteContactTest() throws URISyntaxException{
		addContactTest();
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/contact/1";
		URI uri = new URI(restUrl);
		ResponseEntity<Object> res = restTemplate.execute(uri, HttpMethod.DELETE, null, restTemplate.responseEntityExtractor(Object.class));
		Assertions.assertEquals(200, res.getStatusCodeValue());
	}
	
	/*
	 * To test DELETE /inactiveContacts rest call
	 */
	@Test
	public void deleteAllInactiveContactTest() throws URISyntaxException{
		addContactTest();
		RestTemplate restTemplate = new RestTemplate();
		String restUrl =  getBaseUrl() +"/inactiveContacts";
		URI uri = new URI(restUrl);
		ResponseEntity<Object> res = restTemplate.execute(uri, HttpMethod.DELETE, null, restTemplate.responseEntityExtractor(Object.class));
		Assertions.assertEquals(200, res.getStatusCodeValue());
	}
	
	public String getBaseUrl() {
		return "http://localhost:"+ randomServerPort + "/contactDirectory";
	}

	
}
