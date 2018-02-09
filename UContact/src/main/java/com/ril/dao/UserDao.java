package com.ril.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import com.ril.entity.User;

@Repository
public class UserDao {	
	@Autowired
	private Database database;

	private final String selectSql="select iduser,login,encryptedkey,validationkey,validaccount,hashed_password,role,timestamp_modif_pwd,encryptedkeypwd from user ";
	
	public User findByLoginAndPassword(String login,String password,boolean actif) {
		User u=null;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql = selectSql+"where login=? and password=? and actif=?";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login);
			ps.setString(2,password);
			ps.setBoolean(3,actif);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduser(long iduser,boolean actif) {
		User u=null;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= selectSql+"where iduser=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setLong(1, iduser);
			ps.setBoolean(2, actif);
			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {			
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );	
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
		
	public long Save(User u) throws Exception {
		long result=0;
		
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql = "INSERT INTO USER (iduser,login,encryptedkeypwd,role,encryptedkey,validationkey,validaccount,actif,hashed_password,timestamp_modif_pwd) VALUES (?,?,?,?,?,?,?,?,?,?) "+
						 "ON DUPLICATE KEY UPDATE login=VALUES(login), encryptedkeypwd=VALUES(encryptedkeypwd), role=VALUES(role),encryptedkey=VALUES(encryptedkey),  "+
						 "validationkey=VALUES(validationkey), validaccount=VALUES(validaccount), actif=VALUES(actif),hashed_password=VALUES(hashed_password), timestamp_modif_pwd=VALUES(timestamp_modif_pwd)"; 
							
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1,u.getIduser());
			ps.setString(2,u.getLogin());
			ps.setBytes(3,u.getEncryptedkeypwd());
			ps.setString(4,u.getRole());
			ps.setBytes(5,u.getEncryptedkey());
			ps.setString(6,u.getValidationkey());
			ps.setBoolean(7,u.getValidaccount());
			ps.setBoolean(8,u.isActif());
			ps.setBytes(9,u.getHashedPassword());
			ps.setTimestamp(10,u.getTimestampModifPwd());
			
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
		
		if (result==0) 
			throw new Exception(String.format("Erreur dans UserDa.Save: user=%s",u.toString()));

		return result;
	}
	
	public User findByLogin(String login,boolean actif) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= selectSql+"where login=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login);
			ps.setBoolean(2, actif);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );	
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduserAndValidationkey(Long iduser, String validationkey,boolean actif) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= selectSql+"where iduser=? and validationkey=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ps.setString(2, validationkey);
			ps.setBoolean(3,actif);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );			
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}

	public User findByLoginAndHashedPassword(String login,byte[] hashedPassword,boolean actif) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql=selectSql+"where login=? and hashed_Password=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login);
			ps.setBytes(2, hashedPassword);
			ps.setBoolean(3, actif);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );			
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public User findByIduserAndEncryptedkey(Long iduser, byte[] key,boolean actif) {
		User u=null;

		try {
			Connection conn = database.getSqlConnection();		
			
			String sql= selectSql+"where iduser=? and encryptedkey=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ps.setBytes(2, key);
			ps.setBoolean(3, actif);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public int ActiverDesactiverByIduser(long iduser, boolean actif) throws Exception {
		int result = 0;

		try {
			Connection conn = database.getSqlConnection();

			String sql = "update user set actif=? where iduser=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, actif);
			ps.setLong(2, iduser);
			
			result = ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result == 0)
			throw new Exception(
					String.format("Erreur dans user.ActiverDesactiverByIduser: iduser=%d, actif=%s",iduser, actif));

		return result;
	}
	
	public int ActiverDesactiverUser(User u, boolean actif) throws Exception {
		return ActiverDesactiverByIduser(u.getIduser(),actif);
	}

	
	public User findByIduserAndEncryptedkeypwd(Long iduser, byte[] key,boolean actif)
	{
		User u=null;
				
		try {
			Connection conn = database.getSqlConnection();		
			
			String sql=selectSql+"where iduser=? and encryptedkey=? and actif=?";
			
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);
			ps.setBytes(2, key);
			ps.setBoolean(3, actif);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				u = new User(rs.getLong(1),rs.getString(2),rs.getBytes(3),rs.getString(4),rs.getBoolean(5),false,
						rs.getBytes(6),"",rs.getString(7),true,rs.getTimestamp(8),rs.getBytes(9) );				
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
