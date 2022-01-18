package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class Person implements InterfaceOne {
	static Scanner sc = new Scanner(System.in);
	static String firstName, lastName, address, state, email, city;
	static int zip;
	static long phoneNumber;
	ContactInfo infoContact = new ContactInfo();
	Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
	static Map<String, Contact> detailsBook = new HashMap<>();
	static Map<String, Contact> personByCity = new HashMap<>();
	Map<String, Contact> personByState = new HashMap<>();
	static Map<String, Map<String, Contact>> book = new HashMap<>();

	public void createContact() {
		System.out.println("Enter how many contacts you want to create");
		int numOfContact = sc.nextInt();
		while (numOfContact > 0) {
			System.out.println("Enter Name of person");
			String firstName = sc.next();
			if (!detailsBook.containsKey(firstName)) {
				infoContact.info();
				detailsBook.put(firstName,
						new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
				numOfContact--;
			} else {
				System.out.println("Contact already exists!");
			}
		}
	}

	public void addContact() {
		System.out.println("Enter how many contacts you want to add");
		int numOfContacts = sc.nextInt();
		while (numOfContacts > 0) {
			System.out.println("Enter Name of person");
			String firstName = sc.next();
			if (detailsBook.containsKey(firstName)) {
				System.out.println("Contact already exists!");
				return;
			} else {
				infoContact.info();
				detailsBook.put(firstName,
						new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
				numOfContacts--;
			}
		}
	}

	public void editContact() {
		System.out.println("Enter name of person whose contact you want to edit");
		String firstName = sc.next();
		if (detailsBook.containsKey(firstName)) {
			infoContact.info();
			detailsBook.put(firstName, new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
		} else {
			System.out.println("Contact is not present in book");
			return;
		}
	}

	public void deleteContact() {
		System.out.println("Enter name of person whose contact you want to delete");
		String name = sc.next();
		if (detailsBook.containsKey(name)) {
			detailsBook.remove(name);
		} else {
			System.out.println("Contact is not present in book");
			return;
		}
	}

	public void display() {
		System.out.println("Created contact list is");
		for (Map.Entry m : detailsBook.entrySet()) {
			System.out.println(m.getKey() + "--> " + m.getValue());
		}
	}

	public void addToBook() {
		System.out.println("Enter Name of Address Book");
		String bookName = sc.next();
		if (!book.containsKey(bookName)) {
			book.put(bookName, detailsBook);
		} else {
			System.out.println("Book already exists!");
		}
	}

	public void operation() {
		System.out.println("Enter how many address books you want to add");
		int noOfAddressBook = sc.nextInt();
		while (noOfAddressBook > 0) {
			addContact();
			addToBook();
			noOfAddressBook--;
		}
	}

	public void searchPerson() {
		System.out.println("Enter person name to search ");
		String name = sc.next();
		Map<String, Contact> searchedPerson = detailsBook.entrySet().stream().filter(e -> e.getKey().equals(name))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		System.out.println(searchedPerson);
	}

	public void displayBook() {
		System.out.println("Address Books are:");
		for (Map.Entry e : book.entrySet()) {
			System.out.println(e.getKey() + "--> " + e.getValue());
		}
	}

	public void addContactByCity() {
		System.out.println("Enter how many contacts you want to add");
		int numOfContacts = sc.nextInt();
		while (numOfContacts > 0) {
			System.out.println("Enter Name of city");
			String cityName = sc.next();
			if (personByCity.containsKey(cityName)) {
				System.out.println("Contact already exists!");
				return;
			} else {
				infoContact.info();
				personByCity.put(city, new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
				numOfContacts--;
			}
		}
		System.out.println(personByCity);
	}

	public void addContactByState() {
		System.out.println("Enter how many contacts you want to add");
		int numOfContacts = sc.nextInt();
		while (numOfContacts > 0) {
			System.out.println("Enter Name of city");
			String stateName = sc.next();
			if (personByState.containsKey(stateName)) {
				System.out.println("Contact already exists!");
				return;
			} else {
				infoContact.info();
				personByState.put(state,
						new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
				numOfContacts--;
			}
		}
	}

	public void viewByCity() {
		System.out.println("Enter city name to search ");
		String cityName = sc.next();
		Map<String, Contact> detailsByCity = personByCity.entrySet().stream().filter(e -> e.getKey().equals(cityName))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		if (detailsByCity != null) {
			System.out.println(detailsByCity);
			System.out.println("Number of person belonging to city is: " + detailsByCity.size());
		} else {
			System.out.println("Person having particular city is not present");
		}
	}

	public void viewByState() {
		System.out.println("Enter state name to search ");
		String stateName = sc.next();
		Map<String, Contact> detailsByState = personByState.entrySet().stream()
				.filter(e -> e.getKey().equals(stateName))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		if (detailsByState != null) {
			System.out.println(detailsByState);
			System.out.println("Number of person belonging to state is: " + detailsByState.size());
		} else {
			System.out.println("Person with particular state is not present");
		}
	}

	public void sortAddressBook() {

		Map<String, Contact> sortedContact = detailsBook.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		System.out.println("Sorted Address Book " + sortedContact.toString());

	}

	public void sortByCity() {

		Map<String, Contact> sortedByCity = personByCity.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		System.out.println("Sorted Address Book " + sortedByCity);

	}

	public void sortByState() {

		Map<String, Contact> sortedByState = personByState.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		System.out.println("Sorted Address Book " + sortedByState);

	}

	public void writeToAddressBookFile() {

		String bookName = "personByCity";
		String fileName = bookName + ".txt";

		StringBuffer addressBookBuffer = new StringBuffer();
		personByCity.values().stream().forEach(contact -> {
			String personDataString = contact.toString().concat("\n");
			addressBookBuffer.append(personDataString);
		});

		try {
			Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<String> readDataFromFile() {

		List<String> addressList = new ArrayList<String>();
		String bookName = "personByCity";
		String fileName = bookName + ".txt";
		System.out.println("Reading from : " + fileName + "\n");
		try {
			Files.lines(new File(fileName).toPath()).map(line -> line.trim()).forEach(employeeDetails -> {
				System.out.println(employeeDetails);
				addressList.add(employeeDetails);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressList;
	}

	public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

		String fileName = "./PersonContacts.csv";
		try (Writer writer = Files.newBufferedWriter(Paths.get(fileName));) {

			StatefulBeanToCsvBuilder<Contact> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<Contact> beanWriter = builder.build();
			ArrayList<Contact> listOfContacts = detailsBook.values().stream()
					.collect(Collectors.toCollection(ArrayList::new));
			beanWriter.write(listOfContacts);
			writer.close();
			System.out.println("Written To CSV Successfully !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public <CsvValidationException extends Throwable> void readDataFromCSV()
			throws IOException, CsvValidationException {

		String fileName = "./PersonContacts.csv";
		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {

			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("First Name = " + nextRecord[2]);
				System.out.println("Last Name = " + nextRecord[3]);
				System.out.println("City = " + nextRecord[0]);
				System.out.println("State = " + nextRecord[5]);
				System.out.println("Email = " + nextRecord[1]);
				System.out.println("Phone Number = " + nextRecord[4]);
				System.out.println("Zip Code = " + nextRecord[6]);
				System.out.println("\n");
			}
		}
	}

}
