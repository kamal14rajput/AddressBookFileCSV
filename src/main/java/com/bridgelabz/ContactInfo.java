package com.bridgelabz;

import java.util.Scanner;

public class ContactInfo {
	Scanner sc = new Scanner(System.in);

	public void info() {
		System.out.println("Enter First Name :");
		Person.firstName = sc.next();
		System.out.println("Enter Last Name :");
		Person.lastName = sc.next();
		System.out.println("Enter Address :");
		Person.address = sc.next();
		System.out.println("Enter City :");
		Person.city = sc.next();
		System.out.println("Enter State :");
		Person.state = sc.next();
		System.out.println("Enter Zip :");
		Person.zip = sc.nextInt();
		System.out.println("Enter Phone Number :");
		Person.phoneNumber = sc.nextLong();
		System.out.println("Enter Email :");
		Person.email = sc.next();
	}
}
