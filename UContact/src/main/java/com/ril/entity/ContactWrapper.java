package com.ril.entity;

public class ContactWrapper {
	
	private Contact contact;
	private long idtemplate;
	
	public ContactWrapper() {}

	public long getIdtemplate() {
		return idtemplate;
	}

	public void setIdtemplate(long idtemplate) {
		this.idtemplate = idtemplate;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
