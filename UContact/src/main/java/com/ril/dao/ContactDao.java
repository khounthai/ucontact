package com.ril.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ril.entity.Contact;

@Repository
public class ContactDao {

	@Autowired
	private Database database;

	public List<Contact> findByIduser(long iduser) {

		List<Contact> liste = new ArrayList<Contact>();

		try {
			Connection conn = database.getSqlConnection();

			String sql= "select idcontact,dtcreation,favoris from contact where iduser=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, iduser);

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contact c = new Contact(rs.getLong(1), rs.getDate(2).toLocalDate(), rs.getBoolean(3), iduser);
				liste.add(c);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public long Save(Contact c) {
		long result = 0;

		try {
			Connection conn = database.getSqlConnection();

			String sql = "INSERT INTO CONTACT (idcontact,dtcreation,favoris,iduser) VALUES (?,?,?,?) "+
						"ON DUPLICATE KEY UPDATE dtcreation=VALUES(dtcreation), favoris=VALUES(favoris), iduser=VALUES(iduser)";

			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setLong(1, c.getIdcontact());
			ps.setDate(2, Date.valueOf(c.getdtcreation()));
			ps.setBoolean(3, c.getFavoris());
			ps.setLong(4, c.getIduser());
			
			result = ps.executeUpdate(sql);
			
			ResultSet rspk = ps.getGeneratedKeys();
			rspk.next();
			result = rspk.getLong(1);

			rspk.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
