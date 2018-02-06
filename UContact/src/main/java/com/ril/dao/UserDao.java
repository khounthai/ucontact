package com.ril.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ril.entity.User;

@Repository
public class UserDao {	
	@Autowired
	private Database database;
	
	public User findByLoginAndPassword(String login,String password) {
		User u=null;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql = "select iduser,login,password,role,encrypted_key from user where login=? and password=?";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5));		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduser(long iduser) {
		User u=null;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= "select iduser,login,password,role,encrypted_key from user where iduser=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5));		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public long Save(User u) {
		long result=0;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql = "INSERT INTO USER (login,password,role,encrypted_key) VALUES (?,?,?,?) "+
						 "ON DUPLICATE KEY UPDATE login=VALUES(login), password=VALUES(password), role=VALUES(role),encrypted_key=VALUES(encrypted_key)  "; 
							
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,u.getLogin());
			ps.setString(2,u.getPassword());
			ps.setString(3,u.getRole());
			ps.setBytes(4,u.getEncrypted_key());
			ps.setString(5,u.getLogin());
			ps.setString(6,u.getPassword());
			ps.setString(7,u.getRole());
			ps.setBytes(8,u.getEncrypted_key());
			
			result= ps.executeUpdate(sql);
			
			ResultSet rspk=ps.getGeneratedKeys();
			rspk.next();
			result=rspk.getLong(1);
	
			rspk.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
