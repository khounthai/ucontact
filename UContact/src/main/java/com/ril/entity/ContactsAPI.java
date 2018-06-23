package com.ril.entity;

import java.sql.Date;
import java.util.List;

public class ContactsAPI extends UserAPI {
	List<Contact> contacts;
	
	public ContactsAPI(String l, Date d, String t, List<Contact> c) {
		super(l, d, t);
		contacts = c;
	}
}
