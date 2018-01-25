package com.ril.model;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import com.ril.entity.Contact;

public interface ContactDao extends CrudRepository<Contact, Long> {
	
	public ArrayList<Contact> findByiduser (Long iduser);

}
