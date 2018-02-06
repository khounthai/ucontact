package com.ril.model;

import org.springframework.data.repository.CrudRepository;

import com.ril.entity.User;

public interface UserDao extends CrudRepository<User,Long> {
	
	public User findByIduser(Long id_user);
	
	public User findByLogin(String login);
	
	public User findByLoginAndPassword(String login, String password);
	
	public User findByIduserAndEncryptedkey(Long id_user, byte[] key);
	
}
