package com.bridgelabz;

import java.util.List;

public interface InterfaceOne {
	void createContact();
	void addContact();
	void editContact();
	void deleteContact();
	void display();
	void displayBook();
	void addToBook();
	void operation();
	void searchPerson();
	void addContactByCity();
	void viewByCity();
	void addContactByState();
	void viewByState();
	void sortAddressBook();
	void sortByCity();
	void sortByState();
	void writeToAddressBookFile();
	public List<String> readDataFromFile();
}
