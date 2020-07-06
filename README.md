# Contact Directory Project
**Purpose of the project is to manage the Contact information such First Name, Last Name, Email, Phone Number etc.**<br>
## **Using this project, you can perform following operations**<br>
* **Add the contact to directory**<br>
* **Modify the contact saved in the directory**<br>
* **Delete or set the status of Contact to inactive**<br>
* **Retrieve List of all the contacts**<br>
* **Retrieve List of all active contacts**<br>
* **Retrieve List of all inactive contacts**<br>
* **Delete all inactive contacts from directory**<br>

## **Technology Stack Used**
* **Spring Boot**
* **Java 8**
* **Maven**
* **jUnit**
* **Swagger**

**Note: I have used h2 which is in-memory database in Spring Boot to store contact information.**

## **How to run the application**
* clone the gitHub repo
* Open the cmd at \ContactDirectory\target\ location
* Run following command --> java -jar contact-directory-0.0.1-SNAPSHOT.jar
* After running above command application will start on in-built Apache Tomcat Server.
* Refer below steps to manage contacts in Directory

**Note: You can clone the repository in your local and import the project in any IDE and then run ContactDirectoryApplication.java file.**

## **Save Contact Details**
*	**Rest URL** - http://localhost:9091/contactDirectory/contact
* 	**Method** - POST
* 	**RequestBody** - JSON format of Contact class<br>
					{<br>
						"firstName": "Akshay",<br>
						"phoneNumber" : "9876543210"<br>
					}<br>
**Note - You can add other available parameters also**

## **Modify Contact Details**
*	**Rest URL** - http://localhost:9091/contactDirectory/contact
* 	**Method** - PUT
* 	**RequestBody** - JSON format of Contact class<br>
					{<br>
						"contactId": 1,<br>
						"phoneNumber" : "9876543210"<br>
					}<br>
**Note - ContactId is Mandatory**

## **Retrieve List of All Contacts Details**
*	**Rest URL** - http://localhost:9091/contactDirectory/contacts
* 	**Method** - GET

## **Retrieve List of All Active Contacts Details**
*	**Rest URL** - http://localhost:9091/contactDirectory/contacts?status=active
* 	**Method** - GET

## **Retrieve List of All Inactive Contacts Details**
*	**Rest URL** - http://localhost:9091/contactDirectory/contacts?status=inactive
* 	**Method** - GET

## **Delete Contacts Details**
*   **This call set the status of given contactId to INACTIVE**
*	 **Rest URL** - http://localhost:9091/contactDirectory/contact/{contactId}
* 	**Method** - DELETE

## **Delete All Inactive Contacts Details**
*	 **Rest URL** - http://localhost:9091/contactDirectory/inactiveContacts
* 	**Method** - DELETE

## **H2 DB Console Details**
* **URL** - http://localhost:9091/contactDirectory/h2-console
* **Username** - sa
* **password** - <br>
**Note - Password is not required to login. Directly click on Connect.**

## **Swagger UI Details**
* **URL** - http://localhost:9091/contactDirectory/swagger-ui.html