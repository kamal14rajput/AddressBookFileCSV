package com.bridgelabz;

public class AddressBookMain {
	public static void main(String[] args) {

		InterfaceOne person1 = new Person();
		person1.createContact();
		person1.addContact();
		person1.editContact();
		person1.deleteContact();
		person1.display();
		person1.addToBook();
		person1.operation();
		person1.displayBook();
		person1.searchPerson();
		person1.addContactByCity();
		person1.viewByCity();
		person1.addContactByState();
		person1.viewByState();
		person1.sortAddressBook();
		person1.sortByCity();
		person1.sortByState();
		person1.writeToAddressBookFile();
		person1.readDataFromFile();
	}
}
