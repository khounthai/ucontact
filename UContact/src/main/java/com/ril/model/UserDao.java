package com.ril.model;

import org.springframework.data.repository.CrudRepository;

import com.ril.entity.User;

public interface UserDao extends CrudRepository<User,Long>{
	public User findByLoginAndPassword(String login,String password);
}
