package com.ril.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import com.ril.entity.User;

@Repository
public class UserDao {	
	@Autowired
	private Database database;
	
	public User findByLoginAndPassword(String login,String password) {
		User u=null;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql = "select iduser,login,password,role,encryptedkey,validationkey,validaccount from user where login=? and password=?";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(7));		
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
			
			String sql= "select iduser,login,password,role,encryptedkey,validationkey,validaccount from user where iduser=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(5));		
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
			
			String sql = "INSERT INTO USER (iduser,login,password,role,encryptedkey,validationkey,validaccount) VALUES (?,?,?,?,?,?,?) "+
						 "ON DUPLICATE KEY UPDATE login=VALUES(login), password=VALUES(password), role=VALUES(role),encryptedkey=VALUES(encryptedkey),  "+
						 "validationkey=VALUES(validationkey), validaccount=VALUES(validaccount)"; 
							
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1,u.getIduser());
			ps.setString(2,u.getLogin());
			ps.setString(3,u.getPassword());
			ps.setString(4,u.getRole());
			ps.setBytes(5,u.getEncryptedkey());
			ps.setString(6,u.getValidationkey());
			ps.setBoolean(7,u.getValidaccount());
			
			ps.executeUpdate();
			
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
	
	public User findByLogin(String login) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= "select iduser,login,password,role,encryptedkey,validationkey,validaccount from user where login=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(7));		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduserAndValidationkey(Long iduser, String validationkey) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= "select iduser,login,password,role,encryptedkey,validationkey,validaccount from user where iduser=? and validationkey=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ps.setString(2, validationkey);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(7));			
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}

	public User findByLoginAndHashedPassword(String login,byte[] hashedPassword) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= "select iduser,login,hashedPassword,role,encryptedkey,validationkey,validaccount from user where login=? and hashedPassword=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login);
			ps.setBytes(2, hashedPassword);			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(7));			
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduserAndEncryptedkey(Long iduser, byte[] key) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= "select iduser,login,hashedPassword,role,encryptedkey,validationkey,validaccount from user where iduser=? and encryptedkey=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ps.setBytes(2, key);			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5),rs.getString(6),rs.getBoolean(7));			
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
}
