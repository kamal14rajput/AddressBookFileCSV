package com.bridgelabz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookTest {

	static Map<String, Contact> detailsBook = new HashMap<>();
	static Person person = null;

	@BeforeClass
	public static void createAddressObject() {
		person = new Person();
	}

	@Test
	public void givenDetails_ShouldAddToContactList() {

		detailsBook.put("kamal", new Contact("kamal", "Sant", "Nagar", "Pune", "MH", 12, 0110, "kamal@yahoo.com"));
		int listSize = detailsBook.size();
		Assert.assertEquals(1, listSize);

	}

	@Test
	public void given2Contacts_WhenWrittenToFile_ShouldMatchEntries() {
		AddressBookFileIO addressFileIO = new AddressBookFileIO();
		addressFileIO.writeToAddressBookFile("book1.txt", detailsBook);
		addressFileIO.printData("book1.txt");
		long entries = addressFileIO.countEntries("book1.txt");
		Assert.assertEquals(1, entries);

	}

	@Test
	public void givenFile_WhenRead_ShouldReturnNumberOfEntries() {

		AddressBookFileIO addressFileIO = new AddressBookFileIO();
		List<String> entries = addressFileIO.readDataFromFile("book1.txt");
		long countEntries = entries.size();
		Assert.assertEquals(1, countEntries);
	}

}
