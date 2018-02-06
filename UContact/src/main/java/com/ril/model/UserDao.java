package com.ril.model;

import org.springframework.data.repository.CrudRepository;

import com.ril.entity.User;

public interface UserDao extends CrudRepository<User,Long> {
	

	public User findByLoginAndHashedPassword(String login,byte[] hashedPassword);
	public User findByLogin(String login);
	public User findByIduserAndValidationkey(Long iduser, String validationkey);
	public User findByIduser(Long id_user);		
	public User findByIduserAndEncryptedkey(Long id_user, byte[] key);

	
}
